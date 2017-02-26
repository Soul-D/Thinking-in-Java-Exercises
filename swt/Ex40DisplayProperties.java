package swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import swt.util.SWTApplication;
import swt.util.SWTConsole;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Ex40DisplayProperties implements SWTApplication {

    @Override
    public void createContents(Composite parent) {
        parent.setLayout(new FillLayout());
        Text text = new Text(parent, SWT.WRAP | SWT.V_SCROLL);
        StringWriter props = new StringWriter();
        System.getProperties().list(new PrintWriter(props));
        text.setText(props.toString());
    }

    public static void main(String[] args) {
        SWTConsole.run(new Ex40DisplayProperties(), 800, 600);
    }
}
