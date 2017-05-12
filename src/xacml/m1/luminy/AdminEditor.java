package xacml.m1.luminy;

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

public class AdminEditor {

	protected Shell shlXacmlEditorPanel;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text EditingBox;
	private Text intBox;
	private Button button;
	private Button button_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AdminEditor window = new AdminEditor();
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
		shlXacmlEditorPanel.open();
		shlXacmlEditorPanel.layout();
		while (!shlXacmlEditorPanel.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlXacmlEditorPanel = new Shell();
		shlXacmlEditorPanel.setSize(595, 535);
		shlXacmlEditorPanel.setText("XACML Editor Panel");
		shlXacmlEditorPanel.setLayout(new FormLayout());
		
		EditingBox = new Text(shlXacmlEditorPanel, SWT.BORDER);
		FormData fd_EditingBox = new FormData();
		fd_EditingBox.top = new FormAttachment(0);
		fd_EditingBox.left = new FormAttachment(0, 10);
		EditingBox.setLayoutData(fd_EditingBox);
		formToolkit.adapt(EditingBox, true, true);
		
		intBox = new Text(shlXacmlEditorPanel, SWT.BORDER);
		fd_EditingBox.bottom = new FormAttachment(intBox, 0, SWT.BOTTOM);
		fd_EditingBox.right = new FormAttachment(100, -295);
		intBox.setEditable(false);
		FormData fd_intBox = new FormData();
		fd_intBox.right = new FormAttachment(100, -10);
		fd_intBox.left = new FormAttachment(EditingBox, 6);
		fd_intBox.top = new FormAttachment(0);
		intBox.setLayoutData(fd_intBox);
		formToolkit.adapt(intBox, true, true);
		
		Button DataModelButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		fd_intBox.bottom = new FormAttachment(DataModelButton, -6);
		FormData fd_DataModelButton = new FormData();
		fd_DataModelButton.right = new FormAttachment(100, -10);
		fd_DataModelButton.left = new FormAttachment(0, 290);
		fd_DataModelButton.top = new FormAttachment(0, 426);
		DataModelButton.setLayoutData(fd_DataModelButton);
		formToolkit.adapt(DataModelButton, true, true);
		DataModelButton.setText("Open DataModel view");
		
		Button btnNewButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		fd_DataModelButton.bottom = new FormAttachment(btnNewButton, -6);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(0, 290);
		fd_btnNewButton.right = new FormAttachment(100, -10);
		fd_btnNewButton.top = new FormAttachment(0, 464);
		fd_btnNewButton.bottom = new FormAttachment(100);
		btnNewButton.setLayoutData(fd_btnNewButton);
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("Save File");
		
		button = new Button(shlXacmlEditorPanel, SWT.NONE);
		button.setText("Open DataModel view");
		FormData fd_button = new FormData();
		fd_button.left = new FormAttachment(0, 10);
		fd_button.right = new FormAttachment(DataModelButton, -6);
		fd_button.top = new FormAttachment(DataModelButton, 0, SWT.TOP);
		fd_button.bottom = new FormAttachment(DataModelButton, 0, SWT.BOTTOM);
		button.setLayoutData(fd_button);
		formToolkit.adapt(button, true, true);
		
		button_1 = new Button(shlXacmlEditorPanel, SWT.NONE);
		button_1.setText("Save File");
		FormData fd_button_1 = new FormData();
		fd_button_1.left = new FormAttachment(0, 10);
		fd_button_1.right = new FormAttachment(btnNewButton, -6);
		fd_button_1.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_button_1.bottom = new FormAttachment(btnNewButton, 0, SWT.BOTTOM);
		button_1.setLayoutData(fd_button_1);
		formToolkit.adapt(button_1, true, true);

	}
}
