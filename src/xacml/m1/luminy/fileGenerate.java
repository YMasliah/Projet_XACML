package xacml.m1.luminy;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class fileGenerate {

	private String textResult;
	
	private String generatePolicy (String algorithm){
		return "<Policy xmlns=\"urn:oasis:names:tc:xacml:3.0:core:schema:wd-17\" PolicyId=\"Policy_1\" RuleCombiningAlgId=\"urn:oasis:names:tc:xacml:1.0:rule-combining-algorithm:"+ algorithm +"\" Version=\"1.0\">";
	}
	
	private String generateClose (String beaconName){
		return "</" + beaconName + ">";
	}
	
	private String generateTarget (){
		return "<Target>";
	}
	
	private String generateAnyOf (){
		return "<AnyOf>";
	}
	
	private String generateAllOf(){
		return "<AllOf>";
	}

	private String generateMatch(TreeItem data){
		return "<Match MatchId=\"urn:oasis:names:tc:xacml:1.0:function:"+ data +"\"><AttributeValue DataType=\"http://www.w3.org/2001/XMLSchema#string\">"+ data +"</AttributeValue><AttributeDesignator AttributeId=\"urn:oasis:names:tc:xacml:1.0:action:"+data+"\" Category=\"urn:oasis:names:tc:xacml:3.0:attribute-category:"+data+"\" DataType=\"http://www.w3.org/2001/XMLSchema#"+data+"\" MustBePresent=\"true\"/></Match>";
	}
	
	private String generateRule (String effect, String id){
		return "<Rule Effect=\""+ effect +"\" RuleId=\""+id+"\">";
	}
	
	public String main(Tree tree){
		return "toto";
	}
	
	public void printTree(Node n) {
		if (n instanceof Comment) {

	        System.out.printf("<!-- %s -->", n.getNodeValue());

	    } else if (n instanceof Element) {
	    	
	    	//textResult = textResult.concat(easyNodeName(n));
	        printTrees(n.getChildNodes());
	       // textResult = textResult.concat(easyNodeNameClosed(n));

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
