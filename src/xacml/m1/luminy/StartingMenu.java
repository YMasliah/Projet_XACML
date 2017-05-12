package xacml.m1.luminy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

public class StartingMenu {

	protected static Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StartingMenu window = new StartingMenu();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(397, 236);
		shell.setMinimumSize(397, 236);
		shell.setText("XACML Toolkit Menu");
		shell.setLayout(new FormLayout());
		
		Label lblXacml = formToolkit.createLabel(shell, "XACML 3.0", SWT.CENTER);
		FormData fd_lblXacml = new FormData();
		fd_lblXacml.top = new FormAttachment(0, 33);
		lblXacml.setLayoutData(fd_lblXacml);
		lblXacml.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		
		Button easyViewButton = formToolkit.createButton(shell, "Easy view \r\nfor casual\r\nXACML user",SWT.WRAP | SWT.CENTER);
		easyViewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					EasyView.main(null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		fd_lblXacml.bottom = new FormAttachment(easyViewButton, -6);
		fd_lblXacml.left = new FormAttachment(easyViewButton, 0, SWT.LEFT);
		FormData fd_easyViewButton = new FormData();
		fd_easyViewButton.left = new FormAttachment(0);
		fd_easyViewButton.bottom = new FormAttachment(100);
		easyViewButton.setLayoutData(fd_easyViewButton);
		easyViewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		Button testViewButton = formToolkit.createButton(shell, "View for testing\r\nXACML file", SWT.WRAP | SWT.CENTER);
		fd_lblXacml.right = new FormAttachment(testViewButton, 0, SWT.RIGHT);
		testViewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					TestView.main(null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		FormData fd_testViewButton = new FormData();
		fd_testViewButton.top = new FormAttachment(easyViewButton, 0, SWT.TOP);
		fd_testViewButton.right = new FormAttachment(100);
		fd_testViewButton.bottom = new FormAttachment(100);
		testViewButton.setLayoutData(fd_testViewButton);
		
		Button editorViewButton = formToolkit.createButton(shell, "Editor view \r\nfor admin user\r\n", SWT.WRAP | SWT.CENTER);
		editorViewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					AdminEditor.main(null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		fd_easyViewButton.top = new FormAttachment(editorViewButton, 0, SWT.TOP);
		fd_testViewButton.left = new FormAttachment(editorViewButton, 6);
		fd_easyViewButton.right = new FormAttachment(editorViewButton, -6);
		FormData fd_editorViewButton = new FormData();
		fd_editorViewButton.top = new FormAttachment(0, 98);
		fd_editorViewButton.bottom = new FormAttachment(100);
		fd_editorViewButton.left = new FormAttachment(0, 129);
		fd_editorViewButton.right = new FormAttachment(100, -125);
		editorViewButton.setLayoutData(fd_editorViewButton);

	}
}
