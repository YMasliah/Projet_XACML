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
import org.eclipse.wb.swt.SWTResourceManager;


public class AdminEditor {

	protected Shell shlXacmlEditorPanel;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Button exempleButton;
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
			myDataModel = DataModel.main("");
			
	}

	private void editTreeExemple(){
		tree.removeAll();
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
	      caseValue1.setText("at least one line verified");
	      caseValue1.setData("AnyOf");
	      TreeItem caseValue10 = new TreeItem(caseValue1, 0);
	      caseValue10.setText("access");
	      caseValue10.setData("AllOf:action:access:id:equal");
	      
	      TreeItem case1 = new TreeItem(policy, 0);
	      case1.setText("Case : permit");
	      case1.setData("case:permit");
	      TreeItem caseValue11 = new TreeItem(case1, 0);
	      caseValue11.setText("at least one line verified");
	      caseValue11.setData("AnyOf");
	      TreeItem caseValue2 = new TreeItem(caseValue11, 0);
	      caseValue2.setText("user");
	      caseValue2.setData("AllOf:subject:user:id:equal");
	      TreeItem caseValue3 = new TreeItem(caseValue11, 0);
	      caseValue3.setText("manager");
	      caseValue3.setData("AllOf:subject:manager:id:equal");
	      TreeItem caseValue4 = new TreeItem(caseValue11, 0);
	      caseValue4.setText("candy <= 10");
	      caseValue4.setData("AllOf:Ressource:10:candy:<=");
	      TreeItem caseFinal = new TreeItem(policy, 0);
	      caseFinal.setText("Last case : Deny");
	      caseFinal.setData("last case : deny");
	      caseFinal.getText();
	      myDataModel = DataModel.main("exemple");
}
	
	private void eventCalled(Event event) {

	    Control[] childrens = entryComposite.getChildren();
	    for (int i = 0 ; i < childrens.length; i++) {
	        childrens[i].dispose();
	    }
		if(((String) event.item.getData()).contains("policy")){
			
		    FormText formTextInfo = formToolkit.createFormText(entryComposite, false);
		    formToolkit.paintBordersFor(formTextInfo);
		    formTextInfo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 0, 1));
		    formTextInfo.setText((String) event.item.getData(), false, false);
			
		    Combo comboAlgo = new Combo(entryComposite, SWT.READ_ONLY);
		    comboAlgo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		    formToolkit.adapt(comboAlgo);
		    formToolkit.paintBordersFor(comboAlgo);
		    comboAlgo.setItems(XacmlImplemented.algorithmList);
		    comboAlgo.setText("Choose algorithm");
			
			Button saveButton = new Button(entryComposite, SWT.NONE);
			saveButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						event.item.setData("policy : " + comboAlgo.getText());
						((TreeItem) event.item).setText("policy : " + comboAlgo.getText());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			saveButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));
		    formToolkit.adapt(saveButton, true, true);
		    saveButton.setText("save");
		    
		    /* FormText formText = formToolkit.createFormText(entryComposite, false);
		    formToolkit.paintBordersFor(formText);
		    formText.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 3, 1));
		    formText.setText("Choose default case rule to add", false, false);
		    
		    
		   Combo combo = new Combo(entryComposite, SWT.READ_ONLY);
		    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		    formToolkit.adapt(combo);
		    formToolkit.paintBordersFor(combo);
			combo.setItems(XacmlImplemented.categoryList);
			combo.setText("Choose category");*/
		    
			//cr�e un any of dans le target par default avec la category definie
			/*Button addDefaultButton = new Button(entryComposite, SWT.NONE);
			addDefaultButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						event.item.setData("policy : " + comboAlgo.getText());
						((TreeItem) event.item).setText("policy : " + comboAlgo.getText());
						TreeItem[] temp = ((TreeItem) event.item).getItems();
						for(TreeItem tempbis : temp)
							if(tempbis.getText() == "Default case"){
							      TreeItem caseValue1 = new TreeItem(tempbis, 0);
							      caseValue1.setText("choose value from " + combo.getText());
							      caseValue1.setData("AnyOf:empty");
							}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			addDefaultButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 5, 1));
		    formToolkit.adapt(addDefaultButton, true, true);
		    addDefaultButton.setText("add");*/
			
		    //ajoute une regle, 
			Button addCaseButton = new Button(entryComposite, SWT.NONE);
			addCaseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 6, 1));
		    formToolkit.adapt(addCaseButton, true, true);
		    addCaseButton.setText("add a case to the policy");
		    addCaseButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						TreeItem case1 = new TreeItem((TreeItem) event.item, 0);
					    case1.setText("Case : undefined");
					    case1.setData("case:undefined");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		else if(((String) event.item.getData()).contains("case")){
			
		    if(!((TreeItem) event.item).getText().equals("Default case")){
			    Button removeBranchButton = new Button(entryComposite, SWT.NONE);
			    removeBranchButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 4, 1));
			    formToolkit.adapt(removeBranchButton, true, true);
			    removeBranchButton.setText("remove current position and all childs");
			    removeBranchButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						try {
							((TreeItem) event.item).dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
		    
			    FormText formTextInfo = formToolkit.createFormText(entryComposite, false);
			    formToolkit.paintBordersFor(formTextInfo);
			    formTextInfo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 0, 1));
			    formTextInfo.setText((String) event.item.getData(), false, false);
				
				Combo comboEffect = new Combo(entryComposite, SWT.READ_ONLY);
				comboEffect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			    formToolkit.adapt(comboEffect);
			    formToolkit.paintBordersFor(comboEffect);
			    comboEffect.setItems(XacmlImplemented.effectList);
			    comboEffect.setText("Choose case effect");
			    
				Button saveButton = new Button(entryComposite, SWT.NONE);
				saveButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						try {
							((TreeItem) event.item).setText("Case : ".concat(comboEffect.getText()));
							event.item.setData("case:".concat(comboEffect.getText()));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				saveButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));
			    formToolkit.adapt(saveButton, true, true);
			    saveButton.setText("save");
		    }
			Button addCaseButton = new Button(entryComposite, SWT.NONE);
			addCaseButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						TreeItem case1 = new TreeItem((TreeItem) event.item, 0);
					    case1.setText("at least one line verified");
					    case1.setData("AnyOf");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			addCaseButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
		    formToolkit.adapt(addCaseButton, true, true);
		    addCaseButton.setText("add an \"at least one verified\" group");
		}
		else if(((String) event.item.getData()).equals("AnyOf")){
			Button addCaseButton = new Button(entryComposite, SWT.NONE);
			addCaseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		    formToolkit.adapt(addCaseButton, true, true);
		    addCaseButton.setText("Add a line");
			addCaseButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						TreeItem case1 = new TreeItem((TreeItem) event.item, 0);
					    case1.setText("To be defined");
					    case1.setData("AllOf:empty");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
		    Button removeBranchButton = new Button(entryComposite, SWT.NONE);
		    removeBranchButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
		    formToolkit.adapt(removeBranchButton, true, true);
		    removeBranchButton.setText("remove current position and all childs");	
		    removeBranchButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						((TreeItem) event.item).dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		else if(!((String) event.item.getData()).equals("AllOf:empty")){
		    FormText formTextInfo = formToolkit.createFormText(entryComposite, false);
		    formToolkit.paintBordersFor(formTextInfo);
		    formTextInfo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 0, 1));
		    formTextInfo.setText((String) event.item.getData(), false, false);
		    for(DataItem temp : myDataModel.getVariables()){
		    	if(temp.value.equals(((String) event.item.getData()).split(":")[2])){
		    		formTextInfo.setText(temp.toString(), false, false);
		    		System.out.println("mission complete");
		    	}
		    }
		    			
		    Combo combo = new Combo(entryComposite, SWT.READ_ONLY);
		    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		    formToolkit.adapt(combo);
		    formToolkit.paintBordersFor(combo);
		    combo.add("Choose value");
		    for(DataItem temp : myDataModel.getVariables())
		    	combo.add(temp.value);
			combo.setText("Choose value");
		    
			Combo comboEffect = new Combo(entryComposite, SWT.READ_ONLY);
			comboEffect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		    formToolkit.adapt(comboEffect);
		    formToolkit.paintBordersFor(comboEffect);
		    comboEffect.setItems(XacmlImplemented.matchList);
		    comboEffect.setText("Choose match operator");
			
			Button addCaseButton = new Button(entryComposite, SWT.NONE);
			addCaseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		    formToolkit.adapt(addCaseButton, true, true);
		    addCaseButton.setText("Add");
			addCaseButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						((TreeItem) event.item).setText(combo.getText());
						for(DataItem variable : myDataModel.getVariables())
							if(variable.value.equalsIgnoreCase(combo.getText()))
								event.item.setData("AllOf:"+variable.category+":"+variable.value+":"+variable.identifier+":equal");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			Button removeCaseButton = new Button(entryComposite, SWT.NONE);
			removeCaseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 2));
		    formToolkit.adapt(removeCaseButton, true, true);
		    removeCaseButton.setText("Remove");
		    removeCaseButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
		    Button removeBranchButton = new Button(entryComposite, SWT.NONE);
		    removeBranchButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
		    formToolkit.adapt(removeBranchButton, true, true);
		    removeBranchButton.setText("remove current position and all childs");
		    removeBranchButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						((TreeItem) event.item).dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		else{
		    Combo combo = new Combo(entryComposite, SWT.READ_ONLY);
		    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		    formToolkit.adapt(combo);
		    formToolkit.paintBordersFor(combo);
		    combo.add("Choose value");
		    for(DataItem temp : myDataModel.getVariables())
		    	combo.add(temp.value);
			combo.setText("Choose value");
			
			Button modifyCaseButton = new Button(entryComposite, SWT.NONE);
			modifyCaseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		    formToolkit.adapt(modifyCaseButton, true, true);
		    modifyCaseButton.setText("save");
		    modifyCaseButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						((TreeItem) event.item).setText(combo.getText());
						for(DataItem variable : myDataModel.getVariables())
							if(variable.value.equalsIgnoreCase(combo.getText()))
								event.item.setData("AllOf:"+variable.category+":"+variable.value+":"+variable.identifier+":equal");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		 			
		    Button removeBranchButton = new Button(entryComposite, SWT.NONE);
		    removeBranchButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
		    formToolkit.adapt(removeBranchButton, true, true);
		    removeBranchButton.setText("remove current position and all childs");	
		    removeBranchButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					try {
						((TreeItem) event.item).dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
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
		shlXacmlEditorPanel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shlXacmlEditorPanel.setSize(595, 535);
		shlXacmlEditorPanel.setText("XACML Editor Panel");
		shlXacmlEditorPanel.setLayout(new FormLayout());
		
		Button dataModelButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		exempleButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		Button saveButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		showFileGeneratedButton = new Button(shlXacmlEditorPanel, SWT.NONE);
		entryComposite = new Composite(shlXacmlEditorPanel, SWT.NONE);
		entryComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tree = new Tree(shlXacmlEditorPanel, SWT.BORDER);
		
		
		exempleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					editTreeExemple();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
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

		dataModelButton.setLayoutData(fd_dataModelButton);
		formToolkit.adapt(dataModelButton, true, true);
		dataModelButton.setText("Open DataModel view");
		fd_dataModelButton.top = new FormAttachment(entryComposite, -6);
		fd_dataModelButton.right = new FormAttachment(100, -10);
		fd_dataModelButton.left = new FormAttachment(50, 10);
		fd_dataModelButton.bottom = new FormAttachment(100, -36);
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		FormData fd_saveButton = new FormData();
		fd_saveButton.top = new FormAttachment(dataModelButton, 6);
		fd_saveButton.left = new FormAttachment(50, 10);
		fd_saveButton.right = new FormAttachment(100, -10);
		fd_saveButton.bottom = new FormAttachment(100);
		saveButton.setLayoutData(fd_saveButton);
		formToolkit.adapt(saveButton, true, true);
		saveButton.setText("Save File");
		exempleButton.setText("Exemple");
		FormData fd_exempleButton = new FormData();
		fd_exempleButton.left = new FormAttachment(0, 10);
		fd_exempleButton.right = new FormAttachment(dataModelButton, -6);
		fd_exempleButton.top = new FormAttachment(dataModelButton, 0, SWT.TOP);
		fd_exempleButton.bottom = new FormAttachment(dataModelButton, 0, SWT.BOTTOM);
		exempleButton.setLayoutData(fd_exempleButton);
		formToolkit.adapt(exempleButton, true, true);
		

		showFileGeneratedButton.setText("Show file generated");
		FormData fd_showFileGeneratedButton = new FormData();
		fd_showFileGeneratedButton.right = new FormAttachment(exempleButton, 0, SWT.RIGHT);
		fd_showFileGeneratedButton.top = new FormAttachment(dataModelButton, 6);
		fd_showFileGeneratedButton.bottom = new FormAttachment(100);
		fd_showFileGeneratedButton.left = new FormAttachment(0, 10);
		showFileGeneratedButton.setLayoutData(fd_showFileGeneratedButton);
		formToolkit.adapt(showFileGeneratedButton, true, true);
		
		
		FormData fd_tree = new FormData();
		fd_tree.bottom = new FormAttachment(100, -76);
		fd_tree.top = new FormAttachment(0);
		fd_tree.right = new FormAttachment(100, -295);
		fd_tree.left = new FormAttachment(0, 10);
		tree.setLayoutData(fd_tree);
		formToolkit.adapt(tree);
		formToolkit.paintBordersFor(tree);
		
		
		fd_dataModelButton.top = new FormAttachment(entryComposite, 6);
		FormData fd_entryComposite = new FormData();
		fd_entryComposite.bottom = new FormAttachment(100, -76);
		fd_entryComposite.top = new FormAttachment(0);
		fd_entryComposite.right = new FormAttachment(tree, 285, SWT.RIGHT);
		fd_entryComposite.left = new FormAttachment(tree, 6);
		entryComposite.setLayoutData(fd_entryComposite);
		formToolkit.adapt(entryComposite);
		formToolkit.paintBordersFor(entryComposite);
		GridLayout gridLayout = new GridLayout();
	    entryComposite.setLayout(gridLayout);

	}
}
