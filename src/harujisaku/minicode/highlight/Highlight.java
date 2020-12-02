package harujisaku.minicode.highlight;

import java.util.regex.*;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleContext;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Highlight{
	AbstractHighlight highlight;

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
	
	public Highlight(AbstractHighlight highlight,JTextPane editor){
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
			System.out.println(highlight.MAGENTA_KEYWORDS);
			for (String str :highlight.MAGENTA_KEYWORDS ) {
				System.out.println(str);
			}
	}
	
	private String makeRegex(String[] keywords){
		StringBuilder buff = new StringBuilder("");
		buff.append("(");
		for (String keyword :keywords ) {
				buff.append("\\b").append(keyword).append("\\b").append("|");
		}
		buff.deleteCharAt(buff.length() - 1);
		buff.append(")");
		return buff.toString();
	}
	
	public void highlight(){
		clearTextColors();
		highlight(BLACK_KEYWORDS_REGEX,Color.BLACK);
		highlight(BLUE_KEYWORDS_REGEX,Color.BLUE);
		highlight(CYAN_KEYWORDS_REGEX,Color.CYAN);
		highlight(DARK_GRAY_KEYWORDS_REGEX,Color.DARK_GRAY);
		highlight(GRAY_KEYWORDS_REGEX,Color.GRAY);
		highlight(GREEN_KEYWORDS_REGEX,Color.GREEN);
		highlight(LIGHT_GRAY_KEYWORDS_REGEX,Color.LIGHT_GRAY);
		highlight(MAGENTA_KEYWORDS_REGEX,Color.MAGENTA);
		highlight(ORANGE_KEYWORDS_REGEX,Color.ORANGE);
		highlight(PINK_KEYWORDS_REGEX,Color.PINK);
		highlight(RED_KEYWORDS_REGEX,Color.RED);
		highlight(WHITE_KEYWORDS_REGEX,Color.WHITE);
		highlight(YELLOW_KEYWORDS_REGEX,Color.YELLOW);
	}
	
	private  void highlight(String regex,Color c){
		Pattern p = Pattern.compile(regex);
		System.out.println(p.pattern());
		Matcher m = p.matcher(editor.getText());
		while(m.find()){
			StyleContext sc = StyleContext.getDefaultStyleContext();
			AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground,c);
			editordoc.setCharacterAttributes(m.start(),m.end()-m.start(),aset,true);
		}
	}
	
	public void clearTextColors(){
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
				StyleConstants.Foreground, Color.BLACK);
		editordoc.setCharacterAttributes(0,editor.getText().length(), aset, true);
	}
}
