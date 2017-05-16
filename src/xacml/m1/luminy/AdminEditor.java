package xacml.m1.luminy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;


public class AdminEditor {

	protected Shell shlXacmlEditorPanel;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Button chooseDefaultTemplateButton;
	private Button showFileGeneratedButton;
	private Tree tree;
	private DataModel myDataModel;
	Composite entryComposite;

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
		              eventCalled(event);
		          }
		      });
		      policy.setText("policy : 'choose an algorithm' ");
		      policy.setData("policy");
		      TreeItem defaultCase = new TreeItem(policy, 0);
		      defaultCase.setText("add variable which are trop for all cases");
		      defaultCase.setData("case");
		      TreeItem case1 = new TreeItem(policy, 0);
		      case1.setText("add variables which need to be present for this case");
		      case1.setData("case");
		      
	}

	private void eventCalled(Event event) {
	  
	    Control[] childrens = entryComposite.getChildren();
	    for (int i = 0 ; i < childrens.length; i++) {
	        childrens[i].dispose();
	    }
		if(((String) event.item.getData()).contains("policy")){
		    Combo comboAlgo = new Combo(entryComposite, SWT.READ_ONLY);
		    comboAlgo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 0, 1));
		    formToolkit.adapt(comboAlgo);
		    formToolkit.paintBordersFor(comboAlgo);
		    comboAlgo.setItems(XacmlImplemented.algorithmList);
		    comboAlgo.setText("Choose algorithm");
			
			Button saveButton = new Button(entryComposite, SWT.NONE);
			saveButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						TreeItem[] tempTree = tree.getItems();
						for(TreeItem treeItem : tempTree)
							if(((String) treeItem.getData()).contains("policy")){
								treeItem.setData("policy : " + comboAlgo.getText());
								treeItem.setText("policy : " + comboAlgo.getText());
								break;
							}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			saveButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		    formToolkit.adapt(saveButton, true, true);
		    saveButton.setText("save");
		    
		    FormText formText = formToolkit.createFormText(entryComposite, false);
		    formToolkit.paintBordersFor(formText);
		    formText.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		    formText.setText("Choose default rule to add", false, false);
		    
		    Combo combo = new Combo(entryComposite, SWT.READ_ONLY);
		    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		    formToolkit.adapt(combo);
		    formToolkit.paintBordersFor(combo);
			combo.setItems(XacmlImplemented.categoryList);
			combo.setText("Choose category");
		    
		}
		else if(event.item.getData() == "case"){
			Combo combo = new Combo(entryComposite, SWT.NONE);
		    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		    formToolkit.adapt(combo);
		    formToolkit.paintBordersFor(combo);
			combo.setItems(DataItem.categoryList);
			combo.setText("choose category");
		}
		entryComposite.layout();
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
		
		Button dataModelButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		dataModelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					if(myDataModel == null)
						myDataModel = DataModel.main("");
					else
						DataModel.main(myDataModel);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		FormData fd_dataModelButton = new FormData();
		fd_dataModelButton.right = new FormAttachment(100, -10);
		fd_dataModelButton.left = new FormAttachment(0, 290);
		fd_dataModelButton.top = new FormAttachment(0, 426);
		dataModelButton.setLayoutData(fd_dataModelButton);
		formToolkit.adapt(dataModelButton, true, true);
		dataModelButton.setText("Open DataModel view");
		
		Button saveButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					System.out.println(myDataModel.getVariables().get(1).category);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
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
		fd_tree.bottom = new FormAttachment(chooseDefaultTemplateButton, -6);
		fd_tree.top = new FormAttachment(0);
		fd_tree.right = new FormAttachment(100, -295);
		fd_tree.left = new FormAttachment(0, 10);
		tree.setLayoutData(fd_tree);
		formToolkit.adapt(tree);
		formToolkit.paintBordersFor(tree);
		
		entryComposite = new Composite(shlXacmlEditorPanel, SWT.NONE);
		FormData fd_entryComposite = new FormData();
		fd_entryComposite.bottom = new FormAttachment(dataModelButton, -6);
		fd_entryComposite.top = new FormAttachment(tree, 0, SWT.TOP);
		fd_entryComposite.right = new FormAttachment(tree, 285, SWT.RIGHT);
		fd_entryComposite.left = new FormAttachment(tree, 6);
		entryComposite.setLayoutData(fd_entryComposite);
		formToolkit.adapt(entryComposite);
		formToolkit.paintBordersFor(entryComposite);
		GridLayout gridLayout = new GridLayout();
	    entryComposite.setLayout(gridLayout);

	}
}
