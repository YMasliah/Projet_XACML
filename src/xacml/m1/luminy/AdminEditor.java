package xacml.m1.luminy;

import org.eclipse.swt.widgets.Event;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.FormToolkit;


public class AdminEditor {

	protected Shell shlXacmlEditorPanel;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text intBox;
	private Button chooseDefaultTemplateButton;
	private Button showFileGeneratedButton;
	private Tree tree;

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

	private void editTree(){
		      TreeItem policy = new TreeItem(tree, 0);
		      tree.addListener(SWT.Selection, new Listener() {
		          public void handleEvent(Event event) {
		              intBox.setText(event.item + " was selected");
		          }
		        });
		      policy.setText("policy : 'choose an algorithm' ");
		      TreeItem defaultCase = new TreeItem(policy, 0);
		      defaultCase.setText("add variable which are trop for all cases");
		      TreeItem case1 = new TreeItem(policy, 0);
		      case1.setText("add variables which need to be present for this case");
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlXacmlEditorPanel.open();
		shlXacmlEditorPanel.layout();
		editTree();
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
		
		intBox = new Text(shlXacmlEditorPanel, SWT.BORDER);
		intBox.setEditable(false);
		FormData fd_intBox = new FormData();
		fd_intBox.left = new FormAttachment(0, 290);
		fd_intBox.right = new FormAttachment(100, -10);
		fd_intBox.top = new FormAttachment(0);
		intBox.setLayoutData(fd_intBox);
		formToolkit.adapt(intBox, true, true);
		
		Button dataModelButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		dataModelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					DataModel.main(null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		fd_intBox.bottom = new FormAttachment(dataModelButton, -6);
		FormData fd_dataModelButton = new FormData();
		fd_dataModelButton.right = new FormAttachment(100, -10);
		fd_dataModelButton.left = new FormAttachment(0, 290);
		fd_dataModelButton.top = new FormAttachment(0, 426);
		dataModelButton.setLayoutData(fd_dataModelButton);
		formToolkit.adapt(dataModelButton, true, true);
		dataModelButton.setText("Open DataModel view");
		
		Button saveButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		fd_dataModelButton.bottom = new FormAttachment(saveButton, -6);
		FormData fd_saveButton = new FormData();
		fd_saveButton.left = new FormAttachment(0, 290);
		fd_saveButton.right = new FormAttachment(100, -10);
		fd_saveButton.top = new FormAttachment(0, 464);
		fd_saveButton.bottom = new FormAttachment(100);
		saveButton.setLayoutData(fd_saveButton);
		formToolkit.adapt(saveButton, true, true);
		saveButton.setText("Save File");
		
		chooseDefaultTemplateButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		chooseDefaultTemplateButton.setText("Choose default template");
		FormData fd_chooseDefaultTemplateButton = new FormData();
		fd_chooseDefaultTemplateButton.left = new FormAttachment(0, 10);
		fd_chooseDefaultTemplateButton.right = new FormAttachment(dataModelButton, -6);
		fd_chooseDefaultTemplateButton.top = new FormAttachment(dataModelButton, 0, SWT.TOP);
		fd_chooseDefaultTemplateButton.bottom = new FormAttachment(dataModelButton, 0, SWT.BOTTOM);
		chooseDefaultTemplateButton.setLayoutData(fd_chooseDefaultTemplateButton);
		formToolkit.adapt(chooseDefaultTemplateButton, true, true);
		
		showFileGeneratedButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		showFileGeneratedButton.setText("Show file generated");
		FormData fd_showFileGeneratedButton = new FormData();
		fd_showFileGeneratedButton.left = new FormAttachment(0, 10);
		fd_showFileGeneratedButton.right = new FormAttachment(saveButton, -6);
		fd_showFileGeneratedButton.top = new FormAttachment(saveButton, 0, SWT.TOP);
		fd_showFileGeneratedButton.bottom = new FormAttachment(saveButton, 0, SWT.BOTTOM);
		showFileGeneratedButton.setLayoutData(fd_showFileGeneratedButton);
		formToolkit.adapt(showFileGeneratedButton, true, true);
		
		tree = new Tree(shlXacmlEditorPanel, SWT.BORDER);
		FormData fd_tree = new FormData();
		fd_tree.right = new FormAttachment(intBox, -6);
		fd_tree.bottom = new FormAttachment(intBox, 0, SWT.BOTTOM);
		fd_tree.top = new FormAttachment(0);
		fd_tree.left = new FormAttachment(chooseDefaultTemplateButton, 0, SWT.LEFT);
		tree.setLayoutData(fd_tree);
		formToolkit.adapt(tree);
		formToolkit.paintBordersFor(tree);

	}
}
