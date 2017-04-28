import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomSample {
	
	private int tabCount = 0;
	private boolean anyOfCalled = false;
	private int attributeValueCount = 0;
	private String textResult = "";
	// methodes d'analyse et d'affichage

	public String parseXmlFile(String fileName) throws Exception {

		// preparer une usine a fabriquer les analyseurs DOM
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);

		// construire une analyseur DOM
		DocumentBuilder parser = factory.newDocumentBuilder();

		// analyser et afficher le document XML
		Document doc = parser.parse(new File(fileName));
		printTree(doc);
		return textResult;
	}

	public void printTree(Node n) {
		if (n instanceof Comment) {

	        System.out.printf("<!-- %s -->", n.getNodeValue());

	    } else if (n instanceof Element) {
	    	
	    	easyNodeName(n);
	        printTrees(n.getChildNodes());
	        easyNodeNameClosed(n);

	    } else if (n instanceof Document) {

	        printTrees(n.getChildNodes());

	    }
	}

	private String easyNodeNameClosed(Node n) {
		String newName = "";
		switch (n.getNodeName()) {
        case "AnyOf": 
        	tabCount--;
	        break;
        case "AllOf": 
        	attributeValueCount = 0;
	        break;
        default:
        	newName = ""; 
        	break;
		}

		return newName;
	}

	private String easyNodeName(Node n) {
		String newName = "";
		NamedNodeMap temp;
		
		if(anyOfCalled){
			if(n.getNodeName() == "AttributeDesignator"){
				temp = n.getAttributes();
				for(int i = 0; i < temp.getLength();i++){
					if(temp.item(i).getNodeName() == "Category"){
						newName.concat("\n");
			        	System.out.printf("\n"); 
			        	for(int j = 0; j < tabCount;j++){
			        		newName.concat("\t");
			        		System.out.printf("\t");
			        	}
						if(temp.item(i).getNodeValue().contains("subject")){
							newName.concat("Subject Allowed :");
							System.out.print("Subject Allowed :");
						}
						if(temp.item(i).getNodeValue().contains("resource")){
							newName.concat("Resource Allowed :");
							System.out.print("Resource Allowed :");
						}
						if(temp.item(i).getNodeValue().contains("action")){
							newName.concat("Action Allowed :");
							System.out.print("Action Allowed :");
						}
			        	tabCount++;
					}
				}
				anyOfCalled = false;
			}
		}
		
		switch (n.getNodeName()) {
        case "Target": 
         	for(int j = 0; j < tabCount;j++){
        		newName.concat("\t");
        		System.out.printf("\t");
        	}
	        newName.concat("Acces rules :");  
	        break;
        //depend de ce qu'il va contenir : user, ressources, actions
        case "AnyOf": 
        	anyOfCalled = true;
        	break;
        //un simple retour a la ligne avec X tabulations, X etant le nombre de tabulation deja presente +1.
        /*case "AllOf": 
        	System.out.printf("\n"); 
        	break;*/
        //for Match we say it will always be String-equal, but for later we can get the last part and do something.
        //case "Match": newName = "Next value need to be equal "; System.out.printf(newName); break;
       /* case "AttributeDesignator": 
        	newName = n.getAttributes().item(0).getNodeValue();
        	System.out.printf(" - "); 
        	System.out.printf(newName +" = "); 
        	break;*/
        case "AttributeValue": 
        	if(attributeValueCount != 0)
        		System.out.print(", ");
        	else {
        		System.out.printf("\n"); 
        		for(int i = 0; i < tabCount;i++)
	        		System.out.printf("\t");
        	}
        	attributeValueCount++;
        	newName = n.getChildNodes().item(0).getNodeValue(); 
        	System.out.printf(newName);   
        	break;
        default:
        	newName = ""; 
        	break;
		}

		
		return newName;
	}

	public void printTrees(NodeList nodes) {
		for (int i = 0; (i < nodes.getLength()); i++) {
			printTree(nodes.item(i));
		}
	}

	public void printTrees(NamedNodeMap nodes) {
		for (int i = 0; (i < nodes.getLength()); i++) {
			System.out.printf("%s", nodes.item(i).getNodeValue());
		}
	}
	
}