package harujisaku.minicode.autocomplete.suggest;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

/**
* javaの自動補完設定クラスです.
*/

public class JavaSuggest extends AbstractSuggest{
	
	/**
	* javaでよく使う単語である<br>
	* "abstract  ","break;","catch","class ","case","default","do","else","final ","for","finally","if","import ","interface ","print","println","private ","protected ","public ","return ","static ","throw","try","void ","while","package"<br>を定義済みです.
	*/
	
 	protected String[] defaultWords = {"abstract  ","break;","catch","class ","case","default","do","else","final ","for","finally","if","import ","interface ","print","println","private ","protected ","public ","return ","static ","throw","try","void ","while","package"};
	
	/**
	* デフォルトコンストラクタ.
	* 定義した単語をスーパークラスに設定しています.
	*/
	
	public JavaSuggest(){
		super.defaultWords = this.defaultWords;
		words = new LinkedList<String>(Arrays.asList(defaultWords));
	}
}