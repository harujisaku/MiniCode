package harujisaku.minicode;

import harujisaku.minicode.highlight.*;
import harujisaku.minicode.autocomplete.*;
import harujisaku.minicode.autocomplete.suggest.*;

import java.awt.FontMetrics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import javax.swing.text.DefaultEditorKit;
import javax.swing.text.TabStop;
import javax.swing.text.TabSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class JCodePane extends JTextPane {
	public JCodePane(){
		setTabSize(4);
		getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty,"\n");
		AbstractHighlight highlightStyle = new JavaHighlight();
		Highlight highlight = new Highlight(highlightStyle,this);
		AutoCompletePanel autoComplete = new AutoCompletePanel(this);
		addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e){}

			@Override
			public void keyPressed(KeyEvent e){
				if (Character.isWhitespace(e.getKeyChar())) {
					final int position = getCaretPosition();
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run(){
							highlight.highlight(indexOfHeadOfLine(position-50),indexOfEndOfLine(position+50));
						}
					});
				}else if(e.getKeyCode()==KeyEvent.VK_F1){
					final int position = getCaretPosition();
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run(){
							highlight.highlight();
						}
					});
				}
			}
			@Override
			public void keyReleased(KeyEvent e){}
		});
	}
	
	private void setTabSize(int size){
		FontMetrics fm=getFontMetrics(getFont());
		TabStop[] tabs = new TabStop[10];
		for (int i=0,len=tabs.length;i<len ;i++ ) {
			tabs[i] = new TabStop((i+1)*fm.charWidth(' ')*size);
		}
		TabSet tabSet = new TabSet(tabs);
		SimpleAttributeSet aset=new SimpleAttributeSet();
		StyleConstants.setTabSet(aset,tabSet);
		getStyledDocument().setParagraphAttributes(0,getDocument().getLength(),aset,false);
	}
	
	public int indexOfHeadOfLine(int position){
		if (position<=0) {
			return 0;
		}
		String text=getText();
		char word;
		int count=position;
		while ((word=text.charAt(count))!='\n') {
			count--;
			if (count <= 0) {
				return 0;
			}
		}
		return count;
	}
	
	public int indexOfEndOfLine(int position){
		String text=getText();
		if (position>=text.length()) {
			return text.length();
		}
		char word;
		int count=position,max=text.length()-1;
		while ((word=text.charAt(count))!='\n') {
			count++;
			if (count >= max) {
				return max;
			}
		}
		return count;
	}
	
}