package harujisaku.minicode.replace;

import harujisaku.minicode.replace.*;

import java.util.regex.*;
import javax.swing.text.JTextComponent;

public class FindReplace {
	JTextComponent textComponent;
	String regex=null,text;
	Pattern p;
	Matcher m;
	public FindReplace(JTextComponent textComponent){
		this.textComponent=textComponent;
		text=textComponent.getText();
	}
	
	public void setFindRegex(String regex){
		this.regex=regex;
		try {
			p = Pattern.compile(regex);
			m = p.matcher(text);
		} catch(PatternSyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public Find find(){
		if (regex==null||regex.isEmpty()) {
			return new Find();
		}
		m.find();
		return new Find(m.start(),m.end());
	}
}
