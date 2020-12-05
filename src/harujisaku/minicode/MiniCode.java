package harujisaku.minicode;

import java.util.regex.*;

import harujisaku.minicode.highlight.*;
import harujisaku.minicode.autocomplete.*;
import harujisaku.minicode.autocomplete.suggest.*;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import java.awt.FontMetrics;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.TabStop;
import javax.swing.text.TabSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;

public class MiniCode extends JFrame{
	public static String WHITE_SPACE_REGEX = "[/\\\\(\\)\"':,.;<>~\\!@#$%\\^&*\\|\\+=\\[\\]\\{\\}`\\?\\-…]";
	String title = "untitled";
	JCodePane textPane = new JCodePane();
	AutoCompletePanel autoComplete = new AutoCompletePanel(textPane);
	Highlight highlight;
	
	
	MiniCode(){
		setTitle(title);
		getContentPane().setPreferredSize(new Dimension(500,500));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textPane.setBounds(0,0,500,500);
		textPane.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty,"\n");
		textPane.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e){}

			@Override
			public void keyPressed(KeyEvent e){
				if (Character.isWhitespace(e.getKeyChar())) {
					final int position = textPane.getCaretPosition();
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run(){
							highlight.highlight(indexOfHeadOfLine(position-50),indexOfEndOfLine(position+50));
						}
					});
				}
			}
			@Override
			public void keyReleased(KeyEvent e){}
		});
		AbstractHighlight highlightSyle = new JavaHighlight();
		highlight = new Highlight(highlightSyle,textPane);
		getContentPane().add(textPane);
		setVisible(true);
	}

	private int getTabSize(int index){
		if (index<1) {
			return 0;
		}
		String[] text=textPane.getText().split("\n",-1);
		return 0;
	}

	private void setTabSize(int size){
		FontMetrics fm=textPane.getFontMetrics(textPane.getFont());
		TabStop[] tabs = new TabStop[10];
		for (int i=0,len=tabs.length;i<len ;i++ ) {
			tabs[i] = new TabStop((i+1)*fm.charWidth(' ')*size);
		}
		TabSet tabSet = new TabSet(tabs);
		SimpleAttributeSet aset=new SimpleAttributeSet();
		StyleConstants.setTabSet(aset,tabSet);
		textPane.getStyledDocument().setParagraphAttributes(0,textPane.getDocument().getLength(),aset,false);
	}

	public static void main(String[] args) {
		MiniCode minicode = new MiniCode();
		minicode.myMain(args);
	}
	
	public void myMain(String[] args){
	}
	
	public static String stringRepeat(String text,int count){
		StringBuffer br = new StringBuffer();
		for (int i=0;i<=count ;i++ ) {
			br.append(text);
		}
		return text;
	}
	
	public int indexOfHeadOfLine(int position){
		if (position<=0) {
			return 0;
		}
		String text=textPane.getText();
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
		String text=textPane.getText();
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