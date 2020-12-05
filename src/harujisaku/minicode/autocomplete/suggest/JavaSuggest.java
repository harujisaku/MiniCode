package harujisaku.minicode.autocomplete.suggest;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public class JavaSuggest extends AbstractSuggest{
 	protected String[] defaultWords = {"abstract  ","break;","catch","class ","case","default","do","else","final ","for","finally","if","import ","interface ","print","println","private ","protected ","public ","return ","static ","throw","try","void ","while","package"};
	
	public JavaSuggest(){
		super.defaultWords = this.defaultWords;
		words = new LinkedList<String>(Arrays.asList(defaultWords));
	}
}