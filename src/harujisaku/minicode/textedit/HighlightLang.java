package harujisaku.minicode.textedit;

import harujisaku.minicode.theme.ThemeColor;

import java.awt.Color;

public class HighlightLang {
	public String lang ="text",WHITE_SPACE_REGEX="";
	public String[] DEFAULT_KEYWORDS={""},BLACK_KEYWORDS={""},BLUE_KEYWORDS={""},CYAN_KEYWORDS={""},DARK_GRAY_KEYWORDS={""},GRAY_KEYWORDS={""};
	public String[] GREEN_KEYWORDS={""},MAGENTA_KEYWORDS={""},ORANGE_KEYWORDS={""},PINK_KEYWORDS={""},RED_KEYWORDS={""},WHITE_KEYWORDS={""},YELLOW_KEYWORDS={""},LIGHT_GRAY_KEYWORDS={""};
	private  Color quotation= ThemeColor.BLACK;
	public HighlightLang(String lang){
		this.lang=lang;
	}
	
	public HighlightLang setWhitespace(String str ){
		System.out.println(str);
		WHITE_SPACE_REGEX=str;
		return this;
	}
	
	public HighlightLang setDefault(String  word ){
		String[] str = word.split(",");
		DEFAULT_KEYWORDS=str;
		return this;
	}
	public HighlightLang setBlack(String  word ){
		String[] str = word.split(",");
		BLACK_KEYWORDS=str;
		return this;
	}
	public HighlightLang setBlue(String word ){
		String[] str = word.split(",");
		BLUE_KEYWORDS=str;
		return this;
	}
	public HighlightLang setCyan(String  word ){
		String[] str = word.split(",");
		CYAN_KEYWORDS=str;
		return this;
	}
	public HighlightLang setDarkGray(String  word ){
		String[] str = word.split(",");
		DARK_GRAY_KEYWORDS=str;
		return this;
	}
	public HighlightLang setGray(String  word ){
		String[] str = word.split(",");
		GRAY_KEYWORDS=str;
		return this;
	}
	public HighlightLang setLightGray(String word ){
		String[] str = word.split(",");
		LIGHT_GRAY_KEYWORDS=str;
		return this;
	}
	public HighlightLang setMagenta(String  word ){
		String[] str = word.split(",");
		MAGENTA_KEYWORDS=str;
		return this;
	}
	public HighlightLang setOrange(String word ){
		String[] str = word.split(",");
		ORANGE_KEYWORDS=str;
		return this;
	}
	public HighlightLang setPink(String  word ){
		String[] str = word.split(",");
		PINK_KEYWORDS=str;
		return this;
	}
	public HighlightLang setRed(String word ){
		String[] str = word.split(",");
		RED_KEYWORDS=str;
		return this;
	}
	public HighlightLang setWhite(String word ){
		String[] str = word.split(",");
		WHITE_KEYWORDS=str;
		return this;
	}
	public HighlightLang setYellow(String  word ){
		String[] str = word.split(",");
		YELLOW_KEYWORDS=str;
		return this;
	}
	public HighlightLang setQuotation(String word){
		String[] str = word.split(",");
		if (str.length==1) {
			if (word.equals("BLACK")) {
				quotation=ThemeColor.BLACK;
			}else if (word.equals("BLUE")) {
				quotation=ThemeColor.BLUE;
			}else if(word.equals("CYAN")){
				quotation=ThemeColor.CYAN;
			}else if(word.equals("DARK_GRAY")){
				quotation=ThemeColor.DARK_GRAY;
			}else if(word.equals("GRAY")){
				quotation=ThemeColor.GRAY;
			}else if(word.equals("GREEN")){
				quotation=ThemeColor.GREEN;
			}else if(word.equals("LIGHT_GRAY")){
				quotation=ThemeColor.LIGHT_GRAY;
			}else if(word.equals("MAGENTA")){
				quotation=ThemeColor.MAGENTA;
			}else if(word.equals("ORANGE")){
				quotation=ThemeColor.ORANGE;
			}else if(word.equals("PINK")){
				quotation=ThemeColor.PINK;
			}else if(word.equals("RED")){
				quotation=ThemeColor.RED;
			}else if(word.equals("WHITE")){
				quotation=ThemeColor.WHITE;
			}else if(word.equals("YELLOW")){
				quotation=ThemeColor.YELLOW;
			}
			return this;
		}
		quotation=new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
		return this;
	}
	public Color quotation(){
		return quotation;
	}
}