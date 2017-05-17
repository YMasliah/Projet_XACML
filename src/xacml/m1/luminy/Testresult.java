package xacml.m1.luminy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class Testresult {

	protected Shell shell;
	private String file = "fichier.txt";
	private String rep = result(file);
	protected Shell shlXacmlTestingPanel;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 * @throws Exception 
	 */
	public static void main() throws Exception {
		try {
			Testresult window = new Testresult();
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
		shlXacmlTestingPanel.open();
		shlXacmlTestingPanel.layout();
		while (!shlXacmlTestingPanel.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlXacmlTestingPanel = new Shell();
		shlXacmlTestingPanel.setSize(450, 300);
		shlXacmlTestingPanel.setText("XACML Testing Panel");
		
		text = new Text(shlXacmlTestingPanel, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(0, 0, 434, 193);
		text.setText(rep);
		
		Button btnClose = new Button(shlXacmlTestingPanel, SWT.PUSH);
		btnClose.setBounds(0, 199, 434, 62);
		btnClose.setText("OK (Close)");
	}
	
	public static String result(String fichier){
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+"\n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	return chaine;
	}
}
