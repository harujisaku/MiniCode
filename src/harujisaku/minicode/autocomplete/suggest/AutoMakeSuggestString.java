package harujisaku.minocode.autocomplete.suggest;

public class AutoMakeSuggestString {
	JTextPane textpane;
	AbstractSuggest suggest;
	public AutoMakeSuggestString(JTextPane textpane,AbstractSuggest suggest){
		this.textpane=textpane;
		this.suggest=suggest;
	}
	
	public List<String> scan(int start,int end){
		String text=textpane.getText().substring(start,end);
		List<String> words = new LinkedList<String>();
		if (text.isEmpty()) {
			return {""};
		}
		Pattern p = Pattern.compile("");
		Matcher m = p.matcher(text);
		while(m.find()){
			words.add(m.group(1));
		}
		return words;
	}
	
	public List<String> scanAll(){
		return scan(0,textpane.getText().length()):
	}
	
	public void makeSuggest(){
		suggest.addAll(scanAll());
	}
	
	public void makeSuggest(int start,int end){
		suggest.addAll(scan(start,end));
	}
}