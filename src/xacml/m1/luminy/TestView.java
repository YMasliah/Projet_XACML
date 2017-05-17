package xacml.m1.luminy;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import java.io.*;

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
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
          String  querry = text.getText();
          launch(querry);
			}
		});
	}


public static void launch(String query) { 
	try { 
		String[] commands = { "cmd.exe", "/C", "java -jar valid/XACMLSMT.jar " ,query," >fichier.txt" };
			//{"java"," -jar /bin/XACMLSMT.jar ",query," >fic.txt"}; 
		Runtime.getRuntime().exec(commands);
		Testresult.main();
	} catch (IOException e) { 
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
	} 
}

	
}
