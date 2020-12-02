package harujisaku.minicode;

import java.util.regex.*;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

public class MiniCode extends JFrame{
	int position;
	String title = "untitled";
	JEditorPane editorPane;
	AutoCompletePanel autoComplete;
	MiniCode(){
		setTitle(title);
		getContentPane().setPreferredSize(new Dimension(500,500));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editorPane = new JEditorPane();
		editorPane.setBounds(0,0,500,500);
		editorPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER||e.getKeyChar() == KeyEvent.VK_TAB) {
					if (autoComplete != null) {
						if (autoComplete.insertSelection()) {
							e.consume();
							final int position = editorPane.getCaretPosition();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									try {
										editorPane.getDocument().remove(position - 1, 1);
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
			}
		});
		getContentPane().add(editorPane);
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
	
	final int position = editorPane.getCaretPosition();
	Point location;
	try {
		location = editorPane.modelToView(position).getLocation();
	} catch (BadLocationException e2) {
		e2.printStackTrace();
		return;
	}
	String text = editorPane.getText().replaceAll("\r","");
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
	autoComplete = new AutoCompletePanel(editorPane, position, subWord, location,count);
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			editorPane.requestFocusInWindow();
		}
	});
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
		while(true){
			position=editorPane.getCaretPosition();
		}
	}
}