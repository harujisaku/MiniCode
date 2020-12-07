package harujisaku.minicode.autocomplete.suggest;

import javax.swing.JTextPane;
import java.util.List;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AutoMakeSuggestString {
	JTextPane textpane;
	AbstractSuggest suggest;
	public AutoMakeSuggestString(JTextPane textpane,AbstractSuggest suggest){
		this.textpane=textpane;
		this.suggest=suggest;
	}
	
	public List<String> scan(int start,int end){
		String text=textpane.getText().substring(start,end);
		if (text.isEmpty()) {
			return new LinkedList<String>();
		}
		List<String> words = new LinkedList<String>();
		Pattern p = Pattern.compile("\\w+");
		Matcher m = p.matcher(text);
		while(m.find()){
			words.add(m.group());
		}
		return words;
	}
	
	public List<String> scanAll(){
		return scan(0,textpane.getText().length());
	}
	
	public void makeSuggest(){
		suggest.addAll(scanAll());
	}
	
	public void makeSuggest(int start,int end){
		suggest.addAll(scan(start,end));
	}
}