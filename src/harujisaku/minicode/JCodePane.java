package harujisaku.minicode;

import javax.swing.JTextPane;
import javax.swing.text.DefaultEditorKit;

public class JCodePane extends JTextPane {
	public JCodePane(){
		getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty,"\n");
	}
	
}