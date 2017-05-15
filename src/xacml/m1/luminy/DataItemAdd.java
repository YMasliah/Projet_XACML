package xacml.m1.luminy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DataItemAdd {

	protected Shell shlAddDataItem;
	private Text txtValue;
	private Text txtIdentifier;
	private DataModel dataModel;

	/**
	 * Launch the application.
	 * @param dataModel
	 * @wbp.parser.entryPoint
	 */
	public static void main(DataModel dataModel) {
		try {
			DataItemAdd window = new DataItemAdd();
			window.dataModel = dataModel;
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
		shlAddDataItem.open();
		shlAddDataItem.layout();
		while (!shlAddDataItem.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAddDataItem = new Shell();
		shlAddDataItem.setSize(511, 93);
		shlAddDataItem.setText("Add Data Item Panel");
		
		txtValue = new Text(shlAddDataItem, SWT.BORDER);
		txtValue.setText("Enter value");
		txtValue.setBounds(0, 0, 119, 23);
		
		Combo comboCategory = new Combo(shlAddDataItem, SWT.READ_ONLY);
		comboCategory.setBounds(125, 0, 119, 23);
		comboCategory.setItems(DataItem.categoryList);
		comboCategory.setText("Choose category");
		txtIdentifier = new Text(shlAddDataItem, SWT.BORDER);
		txtIdentifier.setText("Enter identifier");
		txtIdentifier.setBounds(250, 0, 119, 23);
		
		Combo comboType = new Combo(shlAddDataItem, SWT.NONE |SWT.READ_ONLY);
		comboType.setBounds(375, 0, 119, 23);
		comboType.setItems(DataItem.typeList);
		comboType.setText("Choose type");
		
		Button btnAdd = new Button(shlAddDataItem, SWT.NONE);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					dataModel.addDataItem(txtValue.getText(), comboCategory.getText(), txtIdentifier.getText(), comboType.getText());;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(375, 29, 119, 23);
		btnAdd.setText("Add");
		
		Button btnCancel = new Button(shlAddDataItem, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.setBounds(250, 29, 119, 23);

	}
}
