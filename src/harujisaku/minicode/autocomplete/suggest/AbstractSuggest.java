package harujisaku.minicode.autocomplete.suggest;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
/*
正規表現を使用しないでください。正規表現は時間がかかるため候補が出るまでのラグが長くなります。
*/
public abstract class AbstractSuggest {
	protected String[] defaultWords = {""};
	public String[] lastResult = {""};
	public List<String> words;
	public AbstractSuggest(){
		words = new LinkedList<String>(Arrays.asList(defaultWords));
	}
	public String[] search(String text){
		List<String> result = new LinkedList<String>();
		for (final String str : words.toArray(new String[words.size()])) {
			if (text.contains(str.substring(0,Math.min(text.length(),str.length())))) {
				result.add(str);
			}
		}
		lastResult = result.toArray(new String[result.size()]);
		return lastResult;
	}
	
	public boolean hasValue(String text){
		for (final String str :words.toArray(new String[words.size()]) ) {
			if (text.contains(str.substring(0,Math.min(text.length(),str.length())))) {
				return true;
			}
		}
		return false;
	}
}