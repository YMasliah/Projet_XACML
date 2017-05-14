package xacml.m1.luminy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class DataModel {

	protected Shell shlDataModelView;
	private Table table;
	private ArrayList<DataItem> variables = new ArrayList<DataItem>();
	private String file = "DataModel.txt";

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DataModel window = new DataModel();
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
		decodeDataFile();
		shlDataModelView.open();
		shlDataModelView.layout();
		while (!shlDataModelView.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void decodeDataFile(){
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if(!sCurrentLine.startsWith("#") && !sCurrentLine.isEmpty()){
					String[] temp = sCurrentLine.split(";");
					variables.add(new DataItem (temp[0],temp[1],temp[2],temp[3]));
				}
			}
		} catch (IOException f) {
			f.printStackTrace();
		}
		for (int loopIndex = 0; loopIndex < variables.size(); loopIndex++) {
		      TableItem item = new TableItem(table, SWT.NULL);
		      item.setText("Item " + loopIndex);
		      item.setText(0, variables.get(loopIndex).value);
		      item.setText(1, variables.get(loopIndex).category);
		      item.setText(2, variables.get(loopIndex).identifier);
		      item.setText(3, variables.get(loopIndex).type);
		    }
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDataModelView = new Shell();
		shlDataModelView.setSize(429, 314);
		shlDataModelView.setText("Data Model Panel");
		shlDataModelView.setLayout(new FormLayout());
		
		table = new Table(shlDataModelView, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.CHECK);
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0);
		fd_table.left = new FormAttachment(0);
		fd_table.right = new FormAttachment(100);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnVariableName = new TableColumn(table, SWT.NONE);
		tblclmnVariableName.setWidth(100);
		tblclmnVariableName.setText("Value");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Category");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Identifier");
		
		TableColumn tblclmnType = new TableColumn(table, SWT.NONE);
		tblclmnType.setWidth(99);
		tblclmnType.setText("Type");
		
		Button saveFileButton = new Button(shlDataModelView, SWT.NONE);
		FormData fd_saveFileButton = new FormData();
		fd_saveFileButton.right = new FormAttachment(100);
		fd_saveFileButton.bottom = new FormAttachment(100);
		saveFileButton.setLayoutData(fd_saveFileButton);
		saveFileButton.setText("Save");
		
		Button addItemButton = new Button(shlDataModelView, SWT.NONE);
		fd_table.bottom = new FormAttachment(100, -62);
		addItemButton.setText("Add new Item");
		FormData fd_addItemButton = new FormData();
		fd_addItemButton.top = new FormAttachment(table, 6);
		fd_addItemButton.left = new FormAttachment(0);
		addItemButton.setLayoutData(fd_addItemButton);
		
		Button deleteCheckedItemButton = new Button(shlDataModelView, SWT.NONE);
		fd_saveFileButton.left = new FormAttachment(deleteCheckedItemButton, 6);
		fd_addItemButton.bottom = new FormAttachment(deleteCheckedItemButton, -6);
		fd_addItemButton.right = new FormAttachment(100, -215);
		deleteCheckedItemButton.setText("Delete checked item");
		FormData fd_deleteCheckedItemButton = new FormData();
		fd_deleteCheckedItemButton.right = new FormAttachment(addItemButton, 0, SWT.RIGHT);
		fd_deleteCheckedItemButton.left = new FormAttachment(0);
		fd_deleteCheckedItemButton.bottom = new FormAttachment(100);
		deleteCheckedItemButton.setLayoutData(fd_deleteCheckedItemButton);
		
		Button openExistingDataButton = new Button(shlDataModelView, SWT.NONE);
		openExistingDataButton.setText("Open existing Data Model");
		FormData fd_openExistingDataButton = new FormData();
		fd_openExistingDataButton.top = new FormAttachment(table, 6);
		fd_openExistingDataButton.bottom = new FormAttachment(saveFileButton, -6);
		fd_openExistingDataButton.left = new FormAttachment(addItemButton, 6);
		fd_openExistingDataButton.right = new FormAttachment(100);
		openExistingDataButton.setLayoutData(fd_openExistingDataButton);

	}
}
