package harujisaku.minicode.autocomplete.suggest;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.LinkedHashSet;
/**
* 各言語の自動補完のもととなる抽象クラスです.
正規表現を使用しないでください。正規表現は時間がかかるため候補が出るまでのラグが長くなります。
*/
public abstract class AbstractSuggest {
	
	/**
	* 自動で対となる文字を入力する文字を設定します.
	*/
	
	public String REPEAT_LETTER = "[({\"'`";
	
	/**
	* {@link REPEAT_LETTER}の対となる文字を設定します.
	*/
	
	public String REPEAT_LETTER_OTHER = "])}\"'`";
	
	/**
	* デフォルトの自動補完文字列を設定します.
	*/
	
	protected String[] defaultWords = {""};
	
	/**
	* 前回の補完文字列を保持する変数です.
	*/
	
	public String[] lastResult = {""};
	
	/**
	* 自動補完文字列のListです.
	* 動的に追加される文字列も追加されます.
	*/
	
	protected List<String> words;
	
	/**
	* デフォルトコンストラクタ.
	*/
	
	public AbstractSuggest(){
		words = new LinkedList<String>(Arrays.asList(defaultWords));
	}
	
	/**
	* 補完文字列を返します.
	* 動的に作成された補完文字列も含め全ての自動補完文字列の中から一致するものを返します.
	* @param text 補完される文字列
	* @return 自動補完の条件に一致する文字列
	*/
	
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
	
	/**
	* 補完が可能かを返します.
	* 動的に作成された補完文字列も含め全ての自動補完文字列の中から一致するものがある場合にtrueを返します.
	* @param text 補完可能か調べる文字列
	* @return 補完可能な場合true
	*/
	
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