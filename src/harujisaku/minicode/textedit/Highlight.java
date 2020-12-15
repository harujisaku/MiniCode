package harujisaku.minicode.textedit;

import java.util.regex.*;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleContext;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Highlight{
	HighlightLang highlight;

	String BLACK_KEYWORDS_REGEX="";
	String BLUE_KEYWORDS_REGEX="";
	String CYAN_KEYWORDS_REGEX="";
	String DARK_GRAY_KEYWORDS_REGEX="";
	String GRAY_KEYWORDS_REGEX="";
	String GREEN_KEYWORDS_REGEX="";
	String LIGHT_GRAY_KEYWORDS_REGEX="";
	String MAGENTA_KEYWORDS_REGEX="";
	String ORANGE_KEYWORDS_REGEX="";
	String PINK_KEYWORDS_REGEX="";
	String RED_KEYWORDS_REGEX="";
	String WHITE_KEYWORDS_REGEX="";
	String YELLOW_KEYWORDS_REGEX="";
	
	JTextPane editor;
	private StyledDocument editordoc;
	
	/**
	* デフォルトコンストラクタ.
	* @param highlight ハイライトを定義しているクラス
	* @param editor ハイライトするJTextPane
	*/
	
	public Highlight(HighlightLang highlight,JTextPane editor){
		this.highlight=highlight;
		this.editor=editor;
			BLACK_KEYWORDS_REGEX=makeRegex(highlight.BLACK_KEYWORDS);
			BLUE_KEYWORDS_REGEX=makeRegex(highlight.BLUE_KEYWORDS);
			CYAN_KEYWORDS_REGEX=makeRegex(highlight.CYAN_KEYWORDS);
			DARK_GRAY_KEYWORDS_REGEX=makeRegex(highlight.DARK_GRAY_KEYWORDS);
			GRAY_KEYWORDS_REGEX=makeRegex(highlight.GRAY_KEYWORDS);
			GREEN_KEYWORDS_REGEX=makeRegex(highlight.GREEN_KEYWORDS);
			LIGHT_GRAY_KEYWORDS_REGEX=makeRegex(highlight.LIGHT_GRAY_KEYWORDS);
			MAGENTA_KEYWORDS_REGEX=makeRegex(highlight.MAGENTA_KEYWORDS);
			ORANGE_KEYWORDS_REGEX=makeRegex(highlight.ORANGE_KEYWORDS);
			PINK_KEYWORDS_REGEX=makeRegex(highlight.PINK_KEYWORDS);
			RED_KEYWORDS_REGEX=makeRegex(highlight.RED_KEYWORDS);
			WHITE_KEYWORDS_REGEX=makeRegex(highlight.WHITE_KEYWORDS);
			YELLOW_KEYWORDS_REGEX=makeRegex(highlight.YELLOW_KEYWORDS);
			editordoc = editor.getStyledDocument();
	}
	
	private String makeRegex(String[] keywords){
		if (keywords.length<2) {
			if (keywords[0].isEmpty()) {
				return "";
			}
			return keywords[0];
		}
		StringBuilder buff = new StringBuilder("");
		buff.append("(");
		for (String keyword :keywords ) {
			buff.append("\\b").append(keyword).append("\\b").append("|");
		}
		buff.deleteCharAt(buff.length() - 1);
		buff.append(")");
		return buff.toString();
	}
	
	/**
	* ハイライトする
	*/
	
	public void highlight(){
		highlight(0,editor.getText().length());
	}
	
	/**
	* 始点、終点を指定してハイライトする.
	* @param start 始点
	* @param end 終点
	*/
	
	public void highlight(int start ,int end){
		clearTextColors(start,end);
		highlight(start,end,BLACK_KEYWORDS_REGEX,Color.BLACK);
		highlight(start,end,BLUE_KEYWORDS_REGEX,Color.BLUE);
		highlight(start,end,CYAN_KEYWORDS_REGEX,Color.CYAN);
		highlight(start,end,DARK_GRAY_KEYWORDS_REGEX,Color.DARK_GRAY);
		highlight(start,end,GRAY_KEYWORDS_REGEX,Color.GRAY);
		highlight(start,end,GREEN_KEYWORDS_REGEX,Color.GREEN);
		highlight(start,end,LIGHT_GRAY_KEYWORDS_REGEX,Color.LIGHT_GRAY);
		highlight(start,end,MAGENTA_KEYWORDS_REGEX,Color.MAGENTA);
		highlight(start,end,ORANGE_KEYWORDS_REGEX,Color.ORANGE);
		highlight(start,end,PINK_KEYWORDS_REGEX,Color.PINK);
		highlight(start,end,RED_KEYWORDS_REGEX,Color.RED);
		highlight(start,end,WHITE_KEYWORDS_REGEX,Color.WHITE);
		highlight(start,end,YELLOW_KEYWORDS_REGEX,Color.YELLOW);
		quotation(start,end,highlight.quotation());
		highlight(start,end,highlight.WHITE_SPACE_REGEX,Color.BLACK);
	}
	
	private  void highlight(String regex,Color c){
		highlight(0,editor.getText().length(),regex,c);
	}
	
	private void highlight(int start,int end,String regex,Color c){
		if (regex.isEmpty()) {
			return;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(editor.getText().substring(start,end));
		while(m.find()){
			StyleContext sc = StyleContext.getDefaultStyleContext();
			AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground,c);
			editordoc.setCharacterAttributes(m.start()+start,m.end()-m.start(),aset,true);
		}
	}
	
	private void quotation(Color c){
		highlight("\".*\"",c);
	}
	
	private void quotation(int start,int end,Color c){
		highlight(start,end,"\".*\"",c);
	}
	
	/**
	* ハイライトを除去する
	*/
	
	public void clearTextColors(){
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
		StyleConstants.Foreground, Color.BLACK);
		editordoc.setCharacterAttributes(0,editor.getText().length(), aset, true);
	}
	
	/**
	* 始点、終点を指定してハイライトを除去する
	* @param start 始点
	* @param end 終点
	*/
	
	public void clearTextColors(int start,int end){
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
		StyleConstants.Foreground, Color.BLACK);
		editordoc.setCharacterAttributes(start,end-start, aset, true);
	}
}
