import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseAdapter;

public class Main {

	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
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
		shell.setBackground(SWTResourceManager.getColor(0, 0, 0));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Button btnNewButton = formToolkit.createButton(shell, "Save User Rights", SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("wola");
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(0);
		fd_btnNewButton.bottom = new FormAttachment(100);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		Button btnNewButton_1 = formToolkit.createButton(shell, "Revert Changes", SWT.NONE);
		fd_btnNewButton.right = new FormAttachment(btnNewButton_1, -6);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_1.bottom = new FormAttachment(100);
		fd_btnNewButton_1.left = new FormAttachment(0, 134);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		
		Button btnNewButton_2 = formToolkit.createButton(shell, "Show XACML", SWT.NONE);
		fd_btnNewButton_1.right = new FormAttachment(100, -153);
		FormData fd_btnNewButton_2 = new FormData();
		fd_btnNewButton_2.left = new FormAttachment(btnNewButton_1, 6);
		fd_btnNewButton_2.right = new FormAttachment(100);
		fd_btnNewButton_2.top = new FormAttachment(100, -53);
		fd_btnNewButton_2.bottom = new FormAttachment(100);
		btnNewButton_2.setLayoutData(fd_btnNewButton_2);
		
		text = new Text(shell, SWT.BORDER);
		fd_btnNewButton.top = new FormAttachment(text, 6);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100);
		fd_text.bottom = new FormAttachment(100, -59);
		fd_text.top = new FormAttachment(0);
		fd_text.left = new FormAttachment(0);
		text.setLayoutData(fd_text);
		formToolkit.adapt(text, true, true);

	}
}
