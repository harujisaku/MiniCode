package harujisaku.minicode;

import java.util.regex.*;

import harujisaku.minicode.highlight.*;

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
	int position;
	String title = "untitled";
	JTextPane textPane;
	Highlight highlight;
	AutoCompletePanel autoComplete;
	FontMetrics fm;
	
	MiniCode(){
		setTitle(title);
		getContentPane().setPreferredSize(new Dimension(500,500));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textPane = new JTextPane();
		textPane.setBounds(0,0,500,500);
		textPane.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty,"\n");
		fm=textPane.getFontMetrics(textPane.getFont());
		TabStop[] tabs = new TabStop[10];
		for (int i=0,len=tabs.length;i<len ;i++ ) {
			tabs[i] = new TabStop((i+1)*fm.charWidth(' ')*4);
		}
		TabSet tabSet = new TabSet(tabs);
		SimpleAttributeSet aset=new SimpleAttributeSet();
		StyleConstants.setTabSet(aset,tabSet);
		textPane.getStyledDocument().setParagraphAttributes(0,textPane.getDocument().getLength(),aset,false);
		textPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER||e.getKeyChar() == KeyEvent.VK_TAB) {
					if (autoComplete != null) {
						if (autoComplete.insertSelection()) {
							e.consume();
							final int position = textPane.getCaretPosition();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									try {
										textPane.getDocument().remove(position - 1, 1);
									} catch (BadLocationException e) {
										e.printStackTrace();
									}
								}
							});
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN && autoComplete != null) {
					autoComplete.moveDown();
				} else if (e.getKeyCode() == KeyEvent.VK_UP && autoComplete != null) {
					autoComplete.moveUp();
				} else if (Character.isLetterOrDigit(e.getKeyChar())) {
					showSuggestionLater();
				} else if (Character.isWhitespace(e.getKeyChar())) {
					hideSuggestion();
					autoComplete=null;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode()==KeyEvent.VK_SPACE||e.getKeyCode()==KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_TAB)&&highlight!=null) {
					highlight.highlight();
				}
				if (e.getKeyCode()==KeyEvent.VK_ENTER&&autoComplete != null&&autoComplete.insertSelection()) {
					e.consume();
					String insert="";
					for (int i=0,len=getTabSize();i<len ;i++ ) {
						insert +="\t";
					}
					insert="\n"+insert;
					textPane.replaceSelection(insert);
				}
			}
		});
		AbstractHighlight highlightSyle = new JavaHighlight();
		highlight = new Highlight(highlightSyle,textPane);
		getContentPane().add(textPane);
		setVisible(true);
	}
	
	protected void showSuggestionLater(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				showSuggestion();
			}
		});
	}
	public static int clamp(int value, int min, int max) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		}
		return value;
	}
protected void showSuggestion() {
	hideSuggestion();
	autoComplete=null;
	final int position = textPane.getCaretPosition();
	Point location;
	try {
		location = textPane.modelToView(position).getLocation();
	} catch (BadLocationException e2) {
		e2.printStackTrace();
		return;
	}
	String text = textPane.getText();
	int count = 0;
	for (int i=0,len=text.length();i<len ;i++ ) {
		if (text.charAt(i)=='\n') {
			count++;
		}
	}
	int start = Math.max(0, position - 1);
	while (start > 0) {
		if (!Character.isWhitespace(text.charAt(start))) {
			start--;
		} else {
			start++;
			break;
		}
	}
	if (start > position) {
		return;
	}
	String subWord = text.substring(start, position);
	subWord = subWord.replace("\n","");
	subWord = subWord.replaceAll(".*\\s","");
	if (subWord.length() < 2) {
		return;
	}
	autoComplete = new AutoCompletePanel(textPane, position, subWord, location,count);
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			textPane.requestFocusInWindow();
		}
	});
}
	
	private int getTabSize(){
		String str = textPane.getText();
		str = str.substring(0,textPane.getSelectionEnd())+"a";
		String line[] = str.split("\n");
		int i=0;
		line[line.length-1]=line[line.length-1]+"a";
		while(line[line.length-1].substring(i,i+1).equals("\t")){
			i++;
		}
		return i;
	}
	
	private void hideSuggestion(){
		if(autoComplete != null){
			autoComplete.hide();
			autoComplete=null;
		}
	}
	
	public static void main(String[] args) {
		MiniCode minicode = new MiniCode();
		minicode.myMain(args);
	}
	
	public void myMain(String[] args){
	}
}