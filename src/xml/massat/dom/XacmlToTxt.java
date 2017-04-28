package xml.massat.dom;

public class XacmlToTxt {
	private String easyXACML;
	public String main(String file) throws Exception {
		DomSample parser = new DomSample();
		easyXACML = parser.parseXmlFile(file);
		System.out.print(easyXACML);
		return easyXACML;
	}
}
