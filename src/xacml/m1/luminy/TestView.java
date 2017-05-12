package xacml.m1.luminy;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class TestView {

	protected Shell shlXacmlTestingPanel;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestView window = new TestView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlXacmlTestingPanel.open();
		shlXacmlTestingPanel.layout();
		while (!shlXacmlTestingPanel.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlXacmlTestingPanel = new Shell();
		shlXacmlTestingPanel.setSize(450, 300);
		shlXacmlTestingPanel.setText("XACML Testing Panel");
		
		text = new Text(shlXacmlTestingPanel, SWT.BORDER);
		text.setBounds(0, 0, 434, 193);
		
		Button btnNewButton = new Button(shlXacmlTestingPanel, SWT.NONE);
		btnNewButton.setBounds(0, 199, 434, 62);
		btnNewButton.setText("Test Query");

	}

}
