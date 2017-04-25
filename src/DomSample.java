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
	// methodes d'analyse et d'affichage

	public void parseXmlFile(String fileName) throws Exception {

		// preparer une usine a fabriquer les analyseurs DOM
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);

		// construire une analyseur DOM
		DocumentBuilder parser = factory.newDocumentBuilder();

		// analyser et afficher le document XML
		Document doc = parser.parse(new File(fileName));
		printTree(doc);

	}

	public void printTree(Node n) {
	    if (n.getNodeType() == org.w3c.dom.Node.TEXT_NODE) {

	        System.out.print(n.getNodeValue());
	       
	    } else if (n instanceof Comment) {

	        System.out.printf("<!-- %s -->", n.getNodeValue());

	    } else if (n instanceof Element) {

	        System.out.printf("<%s>", n.getNodeName());
	        if(n.hasAttributes())
	        	printTrees(n.getAttributes());
	        printTrees(n.getChildNodes());
	        System.out.printf("</%s>", n.getNodeName());

	    } else if (n instanceof Document) {

	        printTrees(n.getChildNodes());

	    }
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