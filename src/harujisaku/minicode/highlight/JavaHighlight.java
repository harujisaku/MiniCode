package harujisaku.minicode.highlight;

public class JavaHighlight extends AbstractHighlight {
	String [] MAGENTA_KEYWORDS = {"abstract","assert","break","case","catch","class","const","continue","default","do","else","enum","extends","final","finally","for","goto","if","implements","import","instanceof","native","new","package","private","private","public","return","static","strictfp","switch","synchronized","throw","ththrows","transient","try","void","volatile","while"};
	public JavaHighlight(){
		super.MAGENTA_KEYWORDS=this.MAGENTA_KEYWORDS;
	}
}