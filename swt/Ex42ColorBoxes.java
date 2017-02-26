package swt;

import net.mindview.util.DaemonThreadPoolExecutor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import swt.util.SWTApplication;
import swt.util.SWTConsole;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class Ex42CBox extends Canvas implements Runnable {
    class CBoxPaintListener implements PaintListener {
        public void paintControl(PaintEvent e) {
            Color color = new Color(e.display, cColor);
            e.gc.setBackground(color);
            Point size = getSize();
            int xStep = size.x / 6;
            int yStep = size.y / 4;
            int[] points = new int[]{
                    xStep, yStep * 2, xStep * 4, yStep * 3, xStep * 3, yStep, xStep * 2, yStep * 3, xStep * 5, yStep * 2
            };
            e.gc.fillPolygon(points);
            color.dispose();
        }
    }

    private static Random rand = new Random();

    private static RGB newColor() {
        return new RGB(rand.nextInt(255),
                rand.nextInt(255), rand.nextInt(255));
    }

    private int pause;
    private RGB cColor = newColor();

    public Ex42CBox(Composite parent, int pause) {
        super(parent, SWT.NONE);
        this.pause = pause;
        addPaintListener(new CBoxPaintListener());
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                cColor = newColor();
                getDisplay().asyncExec(() -> {
                    try {
                        redraw();
                    } catch (SWTException e) {
                    }
                });
                TimeUnit.MILLISECONDS.sleep(pause);
            }
        } catch (InterruptedException | SWTException e) {
        }
    }
}

public class Ex42ColorBoxes implements SWTApplication {
    private int grid = 12;
    private int pause = 50;

    public void createContents(Composite parent) {
        GridLayout gridLayout = new GridLayout(grid, true);
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        parent.setLayout(gridLayout);
        ExecutorService exec = new DaemonThreadPoolExecutor();
        for (int i = 0; i < (grid * grid); i++) {
            final Ex42CBox cb = new Ex42CBox(parent, pause);
            cb.setLayoutData(new GridData(GridData.FILL_BOTH));
            exec.execute(cb);
        }
    }

    public static void main(String[] args) {
        Ex42ColorBoxes boxes = new Ex42ColorBoxes();
        if (args.length > 0)
            boxes.grid = new Integer(args[0]);
        if (args.length > 1)
            boxes.pause = new Integer(args[1]);
        SWTConsole.run(boxes, 500, 400);
    }
}