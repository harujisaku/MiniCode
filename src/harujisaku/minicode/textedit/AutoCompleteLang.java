package harujisaku.minicode.textedit;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.LinkedHashSet;

public class AutoCompleteLang {
	public String REPEAT_LETTER = "[({\"'`";
	public String REPEAT_LETTER_OTHER = "])}\"'`";
	private String lang,word;
	public String[] lastWords={""};
	private List<String> words;
	public AutoCompleteLang(String lang,String word){
		this.lang=lang;
		this.word=word;
		words = new LinkedList<String>(Arrays.asList(word));
	}
	
	public String getLang(){
		return lang;
	}
	
	public String getWords(){
		return word;
	}
	
	public String[] search(String text){
		List<String> result = new LinkedList<String>();
		for (final String str : words.toArray(new String[words.size()])) {
			if (text.startsWith(str.substring(0,Math.min(text.length(),str.length())))) {
				result.add(str);
			}
		}
		return result.toArray(new String[result.size()]);
	}
	
	public boolean hasValue(String text){
		for (final String str :words.toArray(new String[words.size()]) ) {
			if (text.startsWith(str.substring(0,Math.min(text.length(),str.length())))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	* Listに補完文字列をListで追加します.
	* @param list 追加する文字列のList
	*/
	
	public void addAll(List list){
		words.addAll(list);
		words = new LinkedList<String>(new LinkedHashSet<>(words));
	}
	
	/**
	* Listに補完文字列をStringで追加します.
	* @param word 追加する文字列
	*/
	
	public void add(String word){
		if (!word.isEmpty()&&word!=null) {
			words.add(word);
		}
	}
	
	/**
	* indexでListから削除します.
	* 存在しないindexの場合何もしないか例外を投げます.
	* @param index 削除する補完文字列のindex
	*/
	
	public void remove(int index){
		if (index<words.size()) {
			words.remove(index);
		}
	}
	
	/**
	* StringでListから削除します.
	* @param word 削除する補完文字列.
	* @return 削除に成功した場合true.
	*/
	
	public boolean remove(String word){
		return words.remove(word);
	}
	
	/**
	* 渡された文字が繰り返し対象か判定します.
	* @param c 判定する文字
	* @return 繰り返し対象ならtrue
	*/
	
	public boolean isRepeatLetter(char c){
		int repeatLetterLength = REPEAT_LETTER.length();
		int count=0;
		while (c!=REPEAT_LETTER.charAt(count)) {
			count++;
			if (repeatLetterLength-1<count) {
				return false;
			}
		}
		return true;
	}
	
	/**
	* 渡された文字が繰り返し対象の場合に対となる文字を返します.
	* @param c 判定する文字
	* @return 対となる文字
	*/
	
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