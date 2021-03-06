package xacml.m1.luminy;

public class DataItem {
	String value;
	String category;
	String identifier;
	String type;
	
	DataItem(String value, String category, String identifier,String type){
		this.value = value;
		this.category = category;
		this.identifier = identifier;
		this.type = type;
	}
	
	public String toString(){
		return "value : " + value + ", \ncategory : " + category + ", \nidentifier : " + identifier + ", \ntype : " + type;
	}
	
	public static final String categoryList[] = {"Choose category", "resource", "action", "subject"};
	
	public static final String typeList[] = {"Choose type", "string", "boolean", "integer", "time"};
	
	public enum CategoryList {
		  RESOURCE ("resource"),
		  ACTION ("action"),
		  SUBJECT ("subject"); 
		
		public final String value;
		CategoryList(String value){
			this.value = value;
		}
	}
	
	public enum TypeList{
		STRING ("string"),
		BOOLEAN ("boolean"),
		INTEGER ("integer"),
		TIME ("time");
		public final String value;
		TypeList(String value){
			this.value = value;
		}
	}
	
}
