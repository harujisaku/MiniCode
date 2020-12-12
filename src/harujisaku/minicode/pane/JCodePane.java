package harujisaku.minicode.pane;

import harujisaku.minicode.highlight.*;
import harujisaku.minicode.autocomplete.*;
import harujisaku.minicode.autocomplete.suggest.*;
import harujisaku.minicode.file.*;

import java.io.File;

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

import javax.swing.text.BadLocationException;

public class JCodePane extends JTextPane {
	protected int tabLength=0;
	private boolean wasAutoCompleteShow=false;
	private File file = null;
	protected Highlight highlight = null;
	public JCodePane(){
		new JCodePane(4);
	}
	
	public JCodePane(int tabSize,File file){
		setTabSize(tabSize);
		this.file=file;
		setText(FileManager.loadFile(file)) ;
	}
	
	public JCodePane(int tabSize){
		setTabSize(tabSize);
	}
	
	public void setSyntaxHighLight(AbstractHighlight highlightStyle){
		highlight = new Highlight(highlightStyle,this);
	}
	
	private void init(){
		getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty,"\n");
		AutoCompletePanel autoComplete = new AutoCompletePanel(this);
		addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e){
			}

			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if (!autoComplete.wasShow()) {
						final int position = getCaretPosition();
						tabLength = charCount(getText().substring(indexOfHeadOfLine(position-1),indexOfEndOfLine(position)).replace("\n",""),'\t');
					}
					wasAutoCompleteShow = autoComplete.wasShow();
					System.out.println(tabLength);
				}
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
			public void keyReleased(KeyEvent e){
				if (!wasAutoCompleteShow&&e.getKeyCode()==KeyEvent.VK_ENTER) {
					final int position = getCaretPosition();
					try {
						System.out.println(tabLength);
						getDocument().insertString(position,stringRepeat("\t",tabLength),null);
					} catch(BadLocationException ex) {
						ex.printStackTrace();
					}
				}
			}
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
		int count=position;
		while ((text.charAt(count))!='\n') {
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
		int count=position,max=text.length()-1;
		while ((text.charAt(count))!='\n') {
			count++;
			if (count >= max) {
				return max;
			}
		}
		return count;
	}
	
	public String getLine(int position){
		String text=getText().substring(indexOfHeadOfLine(position-1),indexOfEndOfLine(position));
		return text;
	}
	
	public int charCount(String text,char c){
		int count=0,index=0;
		while(index<text.length()){
			if (text.charAt(count)==c) {
				count++;
			}
			index++;
		}
		return count;
	}
	
	public static String stringRepeat(String text,int count){
		StringBuffer br = new StringBuffer();
		for (int i=0;i<count ;i++ ) {
			br.append(text);
		}
		return br.toString();
	}
	
	public void save(){
		file = FileManager.save(file,getText());
	}
}