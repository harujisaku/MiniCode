package harujisaku.minicode.autocomplete.suggest;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.LinkedHashSet;
/*
正規表現を使用しないでください。正規表現は時間がかかるため候補が出るまでのラグが長くなります。
*/
public abstract class AbstractSuggest {
	public String REPEAT_LETTER = "[({\"'`";
	public String REPEAT_LETTER_OTHER = "])}\"'`";
	protected String[] defaultWords = {""};
	public String[] lastResult = {""};
	protected List<String> words;
	public AbstractSuggest(){
		words = new LinkedList<String>(Arrays.asList(defaultWords));
	}
	public String[] search(String text){
		List<String> result = new LinkedList<String>();
		for (final String str : words.toArray(new String[words.size()])) {
			if (text.startsWith(str.substring(0,Math.min(text.length(),str.length())))) {
				result.add(str);
			}
		}
		lastResult = result.toArray(new String[result.size()]);
		return lastResult;
	}
	
	public boolean hasValue(String text){
		for (final String str :words.toArray(new String[words.size()]) ) {
			if (text.startsWith(str.substring(0,Math.min(text.length(),str.length())))) {
				return true;
			}
		}
		return false;
	}
	
	public void addAll(List list){
		words.addAll(list);
		words = new LinkedList<String>(new LinkedHashSet<>(words));
	}
	
	public void add(String word){
		if (!word.isEmpty()&&word!=null) {
			words.add(word);
		}
	}
	
	public void remove(int index){
		if (index<words.size()) {
			words.remove(index);
		}
	}
	
	public boolean remove(String word){
		return words.remove(word);
	}
	
	public boolean isRepeatLetter(char c){
		int repeatLetterLength = REPEAT_LETTER.length();
		int count=0;
		while (c!=REPEAT_LETTER.charAt(count)) {
			System.out.println(REPEAT_LETTER.charAt(count));
			count++;
			if (repeatLetterLength-1<count) {
				return false;
			}
			System.out.println(count);
		}
		return true;
	}
	
	public String repeatLetter(char c){
		int repeatLetterLength = REPEAT_LETTER.length();
		int count=0;
		while(c!=REPEAT_LETTER.charAt(count)){
			count++;
			if (repeatLetterLength-1<count) {
				return "";
			}
		}
		return String.valueOf(REPEAT_LETTER_OTHER.charAt(count));
	}
}