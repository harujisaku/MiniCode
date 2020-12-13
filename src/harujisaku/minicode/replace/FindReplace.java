package harujisaku.minicode.replace;

import harujisaku.minicode.replace.*;

import java.util.regex.*;
import javax.swing.text.JTextComponent;

/**
* 検索置換を行うクラスです.
* @author harujisaku
* @version 1.0
* @since 1.0
*/

public class FindReplace {
	JTextComponent textComponent;
	String regex=null,text;
	Pattern p;
	Matcher m;
	
	/**
	* デフォルトコンストラクタ
	* @param textComponent 置換する対象のテキストコンポーネント
	*/
	
	public FindReplace(JTextComponent textComponent){
		this.textComponent=textComponent;
		text=textComponent.getText();
	}
	
	/**
	* 検索する正規表現を設定します.
	* @param regex 正規表現
	*/
	
	public void setFindRegex(String regex){
		this.regex=regex;
		try {
			p = Pattern.compile(regex);
			m = p.matcher(text);
		} catch(PatternSyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* 検索をします
	* @return 検索結果を格納したFind
	* @see harujisaku.minicode.replace.Find
	*/
	
	public Find find(){
		if (regex==null||regex.isEmpty()) {
			return new Find();
		}
		m.find();
		return new Find(m.start(),m.end());
	}
}
