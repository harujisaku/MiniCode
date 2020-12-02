package harujisaku.minicode;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SuggestString{
 	final private String[] defaultWords = {"abstract  ","break;","catch","class ","case","default","do","else","final ","for","finally","if","import ","interface ","print","println","private ","protected ","public ","return ","static ","throw","try","void ","while"};
	public List<String> words = new ArrayList<String>(Arrays.asList(defaultWords));
	public String[] serche(String text){
		List<String> result = new ArrayList<String>();
		Pattern p = Pattern.compile("([a-zA-Z])(.*([A-Z])[a-z]+)*");
		for (String str : words.toArray(new String[words.size()])) {
			Matcher m = p.matcher(str);
			if (m.find()&&text.matches("("+m.group(1)+".*(?i)"+m.group(3)+"(?-i).*)|("+str.substring(0,2)+".*)")) {
				result.add(str);
			}
		}
		return result.toArray(new String[result.size()]);
	}
}