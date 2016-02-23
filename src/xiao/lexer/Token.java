package xiao.lexer;

public class Token {
	private String symbolAttr;
	private String rawValue; // name attr
	private String dataTypeAttr; ///?
	private String category;
	public int pos = -2; // pos in a clone instance
	
	public static String category_identifier = "identifier";
	public static String category_operator = "operator";
	public static String category_separator = "separator";
	public static String category_literal = "literal";
	public static String category_other = "other";
	public static String category_keyword = "keyword";
	public static String catagory_dolar = "dolar";
	
	// added by yupeng
	private int startPosition;
	private int endPosition;
	
	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}
	
	// add ended

	public Token(Token t) {
		this.symbolAttr = t.symbolAttr;
		this.rawValue = t.rawValue;
		this.dataTypeAttr = t.dataTypeAttr;
		this.category = t.category;
		
		if(rawValue.indexOf("$") != -1 && category.equals(category_identifier)) {
			this.symbolAttr = "Dolar";
			this.category = catagory_dolar;
		}
	}
	
	public Token(String symbolAttr, String rawValue, String category, String dataType) {
		this.symbolAttr = symbolAttr;
		this.rawValue = rawValue;
		this.category = category;
		this.dataTypeAttr = dataType;
		
		if(rawValue.indexOf("$") != -1 && category.equals(category_identifier)) {
			this.symbolAttr = "Dolar";
			this.category = catagory_dolar;
		}
	}
	
	public String getSymbolAttr() {
		return this.symbolAttr;
	}
	
	public String getRawValue() {
		return this.rawValue;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public String getDataTypeAttr() {
		return this.dataTypeAttr;
	}
	
	@Override
	public String toString() {
		//return String.valueOf(this.pos);
		return this.category + " : " + this.symbolAttr + " : " + this.rawValue; 
	}
}

