package xacml.m1.luminy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class DataModel {

	protected Shell shlDataModelView;
	private Table table;

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
		shlDataModelView.open();
		shlDataModelView.layout();
		while (!shlDataModelView.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDataModelView = new Shell();
		shlDataModelView.setSize(480, 300);
		shlDataModelView.setText("Data Model Panel");
		shlDataModelView.setLayout(new FormLayout());
		
		table = new Table(shlDataModelView, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(0, 194);
		fd_table.top = new FormAttachment(0);
		fd_table.left = new FormAttachment(0);
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
		
		Button btnSave = new Button(shlDataModelView, SWT.NONE);
		fd_table.right = new FormAttachment(btnSave, 0, SWT.RIGHT);
		FormData fd_btnSave = new FormData();
		fd_btnSave.top = new FormAttachment(table, 6);
		fd_btnSave.bottom = new FormAttachment(100);
		fd_btnSave.left = new FormAttachment(0);
		fd_btnSave.right = new FormAttachment(100);
		btnSave.setLayoutData(fd_btnSave);
		btnSave.setText("Save");

	}
}
