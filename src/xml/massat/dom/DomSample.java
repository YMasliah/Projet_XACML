package xml.massat.dom;
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
	private int attributeValueCount = 0;
	private int policyCaseCount = 0;
	
	private String attributeValue;
	private String textResult = "";
	private String matchNumericSymbol = "";
	
	private boolean anyOfCalled = false;
	private boolean matchNumeric = false;
	
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
	    	
	    	textResult = textResult.concat(easyNodeName(n));
	        printTrees(n.getChildNodes());
	        textResult = textResult.concat(easyNodeNameClosed(n));

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
    		newName = newName.concat("\n");
	        break;
        case "Policy":
        	tabCount--;
        	policyCaseCount = 0;
        	break;
        case "Rule":
        	tabCount--;
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
						for(int j = 0; j < tabCount;j++)
			        		newName = newName.concat("\t");
						if(temp.item(i).getNodeValue().contains("subject"))
							newName = newName.concat("Subject Allowed :\n");
						
						if(temp.item(i).getNodeValue().contains("resource"))
							newName = newName.concat("Resource Allowed :\n");
						
						if(temp.item(i).getNodeValue().contains("action"))
							newName = newName.concat("Action Allowed :\n");
						if(!matchNumeric)
							newName = newName.concat("\t" + attributeValue);
						else
							attributeValue = "\t".concat(attributeValue);
						tabCount++;
					}
				}
				if(!matchNumeric)
					attributeValue = "";
				anyOfCalled = false;
			}
		}
		
		switch (n.getNodeName()) {
		//affichage inutil apres reflexion
        /*case "Target": 
        	for(int j = 0; j < tabCount;j++)
        		newName = newName.concat("\t");
         	newName = newName.concat("Acces rules :\n"); 
	        break;*/
        //depend de ce qu'il va contenir : user, ressources, actions
        case "AnyOf": 
        	anyOfCalled = true;
        	break;
        //un simple retour a la ligne avec X tabulations, X etant le nombre de tabulation deja presente +1.
        /*case "AllOf": 
        	System.out.printf("\n"); 
        	break;*/
        //for Match we say it will always be String-equal, but for later we can get the last part and do something.
        case "Match": 
        	if(n.getAttributes().item(0).getNodeValue().contains("integer") || n.getAttributes().item(0).getNodeValue().contains("double")){
        		matchNumeric = true;
        		if(n.getAttributes().item(0).getNodeValue().contains("equal"))
        			matchNumericSymbol = " = ";
        		if(n.getAttributes().item(0).getNodeValue().contains("greater-than"))
        			matchNumericSymbol = " > ";
        		if(n.getAttributes().item(0).getNodeValue().contains("greater-than-or-equal"))
        			matchNumericSymbol = " >= ";
        		if(n.getAttributes().item(0).getNodeValue().contains("less-than"))
        			matchNumericSymbol = " < ";
        		if(n.getAttributes().item(0).getNodeValue().contains("less-than-or-equal"))
        			matchNumericSymbol = " <= ";
        	}
        	break;
        case "AttributeDesignator": 
        	if(matchNumeric){
	        	int indexTmp;
	        	int tabNumber;
	        	
				temp = n.getAttributes();
				for(int i = 0; i < temp.getLength();i++){
					if(temp.item(i).getNodeName() == "AttributeId"){
						tabNumber = attributeValue.lastIndexOf("\t")+1;
			        	for(int j = 0; j < tabNumber;j++)
			        		newName = newName.concat("\t");
						if((indexTmp = temp.item(i).getNodeValue().lastIndexOf(":")) >=0)
							newName = newName.concat(temp.item(i).getNodeValue().substring(indexTmp+1));
						else
							newName = newName.concat(temp.item(i).getNodeValue());
						newName = newName.concat(matchNumericSymbol + attributeValue.substring(tabNumber));
					}
				}
				matchNumeric = false;
				matchNumericSymbol = "";
				attributeValue = "";
        	}
        	break;
        case "Policy":
        	for(int j = 0; j < tabCount;j++){
        		newName = newName.concat("\t");
        	}
        	tabCount++;
        	newName = newName.concat("Algorythm to apply : ");
			temp = n.getAttributes();
			for(int i = 0; i < temp.getLength();i++){
				if(temp.item(i).getNodeName() == "RuleCombiningAlgId"){
					int indexPolicy = temp.item(i).getNodeValue().lastIndexOf("algorithm:");
					newName = newName.concat(temp.item(i).getNodeValue().substring(indexPolicy+10));
					newName = newName.concat("\n");
				}
			}
        	break;
        case "Rule":
        	temp = n.getAttributes();
			for(int i = 0; i < temp.getLength();i++){
				if(temp.item(i).getNodeName() == "Effect"){
					policyCaseCount++;
					for(int j = 0; j < tabCount;j++)
		        		newName = newName.concat("\t");
					newName = newName.concat("Case " + policyCaseCount + ", ");
					newName = newName.concat("Effect : " + temp.item(i).getNodeValue());
					newName = newName.concat("\n");
				}
			}
			tabCount++;
        	break;
        case "AttributeValue": 
        	if(attributeValueCount != 0)
        		newName = newName.concat(", ");
        	else
        		for(int j = 0; j < tabCount;j++)
	        		newName = newName.concat("\t");
        	attributeValueCount++;
        	newName = newName.concat(n.getChildNodes().item(0).getNodeValue());  
        	break;
        default:
        	break;
		}
		
		if(anyOfCalled || matchNumeric){
			attributeValue = newName;
			newName = "";
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