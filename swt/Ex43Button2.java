package swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;
import swt.util.SWTApplication;
import swt.util.SWTConsole;

public class Ex43Button2 implements SWTApplication {
    private Label txt;
    private ButtonListener listener = new ButtonListener();

    class ButtonListener implements Listener {
        @Override
        public void handleEvent(Event event) {
            txt.setText(event.widget.toString());
        }
    }

    @Override
    public void createContents(Composite parent) {
        parent.setLayout(new FillLayout());
        Button
                b1 = new Button(parent, SWT.Arm),
                b2 = new Button(parent, SWT.Arm);
        b1.setText("Button1");
        b2.setText("Button2");
        txt = new Label(parent, SWT.CENTER);
        b1.addListener(SWT.MouseDown, listener);
        b2.addListener(SWT.MouseDown, listener);
    }

    public static void main(String[] args) {
        SWTConsole.run(new Ex43Button2(), 800, 600);
    }
}