package harujisaku.minicode.autocomplete.suggest;

import javax.swing.JTextPane;
import java.util.List;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
* 自動で補完文字列を作成するクラスです.
* 正規表現の\wに当てはまる単語を全てかぶらないように補完文字列に追加していっています.
*/

public class AutoMakeSuggestString {
	JTextPane textpane;
	AbstractSuggest suggest;
	
	/**
	* デフォルトコンストラクタ.
	* @param textpane 補完文字列作成元のJTextPane
	* @param suggest 補完する文字列を定義しているクラス
	*/
	
	public AutoMakeSuggestString(JTextPane textpane,AbstractSuggest suggest){
		this.textpane=textpane;
		this.suggest=suggest;
	}
	
	/**
	* 始点、終点を指定して補完文字列を検索します.
	* @param start 始点
	* @param end 終点
	* @return 補完文字列追加対象
	*/
	
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
	
	/**
	* 補完文字列を検索します.
	* @return 補完文字列追加対象
	*/
	
	public List<String> scanAll(){
		return scan(0,textpane.getText().length());
	}
	
	/**
	* 補完文字列を作成します.
	*/
	
	public void makeSuggest(){
		suggest.addAll(scanAll());
	}
	
	/**
	* 始点、終点を指定して補完文字列を作成します.
	*/
	
	public void makeSuggest(int start,int end){
		suggest.addAll(scan(start,end));
	}
}