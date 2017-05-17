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
	private Composite entryComposite;

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
		      defaultCase.setText("Default case");
		      defaultCase.setData("default case");
		      TreeItem caseValue1 = new TreeItem(defaultCase, 0);
		      caseValue1.setText("Action : access");
		      caseValue1.setData("action : access");
		      TreeItem case1 = new TreeItem(policy, 0);
		      case1.setText("Case 0");
		      case1.setData("case 0");
		      TreeItem caseValue2 = new TreeItem(case1, 0);
		      caseValue2.setText("Subject : user");
		      caseValue2.setData("subject : user");
		      TreeItem caseValue3 = new TreeItem(case1, 0);
		      caseValue3.setText("Subject : manager");
		      caseValue3.setData("subject : manager");
		      TreeItem caseValue4 = new TreeItem(case1, 0);
		      caseValue4.setText("Ressource : candy <= 10");
		      caseValue4.setData("Ressource : candy <= 10");
		      TreeItem caseFinal = new TreeItem(policy, 0);
		      caseFinal.setText("Last case : Deny");
		      caseFinal.setData("last case : deny");
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
		    formText.setText("Choose default case rule to add", false, false);
		    
		    Combo combo = new Combo(entryComposite, SWT.READ_ONLY);
		    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		    formToolkit.adapt(combo);
		    formToolkit.paintBordersFor(combo);
			combo.setItems(XacmlImplemented.categoryList);
			combo.setText("Choose category");
		    
			//crée un any of dans le target par default avec la category definie
			Button addDefaultButton = new Button(entryComposite, SWT.NONE);
			addDefaultButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			addDefaultButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		    formToolkit.adapt(addDefaultButton, true, true);
		    addDefaultButton.setText("add");
			
		    //ajoute une regle, 
			Button addCaseButton = new Button(entryComposite, SWT.NONE);
			addCaseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1));
		    formToolkit.adapt(addCaseButton, true, true);
		    addCaseButton.setText("add a case to the policy");
		}
		else if(((String) event.item.getData()).contains("case")){
			Combo comboEffect = new Combo(entryComposite, SWT.READ_ONLY);
			comboEffect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 0, 1));
		    formToolkit.adapt(comboEffect);
		    formToolkit.paintBordersFor(comboEffect);
		    comboEffect.setItems(XacmlImplemented.effectList);
		    comboEffect.setText("Choose case effect");
		    
			Button saveButton = new Button(entryComposite, SWT.NONE);
			saveButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			saveButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		    formToolkit.adapt(saveButton, true, true);
		    saveButton.setText("save");
			
		    Combo combo = new Combo(entryComposite, SWT.READ_ONLY);
		    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		    formToolkit.adapt(combo);
		    formToolkit.paintBordersFor(combo);
			combo.setItems(XacmlImplemented.categoryList);
			combo.setText("Choose category");
		    
			Button addCaseButton = new Button(entryComposite, SWT.NONE);
			addCaseButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			addCaseButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
		    formToolkit.adapt(addCaseButton, true, true);
		    addCaseButton.setText("add");
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
		saveButton.setEnabled(false);
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
		chooseDefaultTemplateButton.setEnabled(false);
		chooseDefaultTemplateButton.setText("Choose default template");
		FormData fd_chooseDefaultTemplateButton = new FormData();
		fd_chooseDefaultTemplateButton.left = new FormAttachment(0, 10);
		fd_chooseDefaultTemplateButton.right = new FormAttachment(dataModelButton, -6);
		fd_chooseDefaultTemplateButton.top = new FormAttachment(dataModelButton, 0, SWT.TOP);
		fd_chooseDefaultTemplateButton.bottom = new FormAttachment(dataModelButton, 0, SWT.BOTTOM);
		chooseDefaultTemplateButton.setLayoutData(fd_chooseDefaultTemplateButton);
		formToolkit.adapt(chooseDefaultTemplateButton, true, true);
		
		showFileGeneratedButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		showFileGeneratedButton.setEnabled(false);
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
