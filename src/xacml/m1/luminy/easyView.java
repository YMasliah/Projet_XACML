package xacml.m1.luminy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

import xml.massat.dom.XacmlToTxt;

public class EasyView {

	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text textBox;
	private static String easyXACML = "";
	private String XACML = "";
	private boolean currentViewXACML = false;
	private static String file = "hierarchical-resource-policy.xml";

	/**
	 * Launch the application.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		XacmlToTxt txt = new XacmlToTxt();
		easyXACML = txt.main(file);

		try {
			EasyView window = new EasyView();
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
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shell.setSize(450, 300);
		shell.setText("Easy view panel");
		shell.setLayout(new FormLayout());
		
		Button XACMLButton = formToolkit.createButton(shell, "Show XACML", SWT.NONE);
		XACMLButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(!currentViewXACML){
					XACML = "";
					try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	
						String sCurrentLine;
	
						while ((sCurrentLine = br.readLine()) != null) {
							XACML = XACML.concat(sCurrentLine +"\n");
						}
	
					} catch (IOException f) {
						f.printStackTrace();
					}
					textBox.setText(XACML);
					XACMLButton.setText("Show friendly view");
				}
				else{
					textBox.setText(easyXACML);
					XACMLButton.setText("Show XACML file");
				}
				currentViewXACML = !currentViewXACML;	
			}
		});
		FormData fd_XACMLButton = new FormData();
		fd_XACMLButton.right = new FormAttachment(100);
		fd_XACMLButton.top = new FormAttachment(100, -53);
		fd_XACMLButton.bottom = new FormAttachment(100);
		XACMLButton.setLayoutData(fd_XACMLButton);
		
		textBox = new Text(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		textBox.setEditable(false);
		fd_XACMLButton.left = new FormAttachment(textBox, 0, SWT.LEFT);
		if(easyXACML.equalsIgnoreCase(""))
			textBox.setText("File not found");
		else
			textBox.setText(easyXACML);
		textBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
		FormData fd_textBox = new FormData();
		fd_textBox.right = new FormAttachment(100);
		fd_textBox.bottom = new FormAttachment(100, -59);
		fd_textBox.top = new FormAttachment(0);
		fd_textBox.left = new FormAttachment(0);
		textBox.setLayoutData(fd_textBox);
		formToolkit.adapt(textBox, true, true);

	}
}
