package harujisaku.minicode;

import javax.swing.JFrame;
import java.awt.Point;
import javax.swing.JEditorPane;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import harujisaku.minicode.*;
import javax.swing.text.BadLocationException;
import javax.swing.SwingUtilities;

public class MiniCode extends JFrame{
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
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
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
	
	protected void showSuggestion(){
		hideSuggestion();
		autoComplete=null;
		String text = editorPane.getText();
		final int position = editorPane.getCaretPosition();
		int count = 0;
		for (int i = 0; i < position; i++) {
			if (text.charAt(i) == '\n') {
				count++;
			}
		}
		Point location;
		try {
			location = editorPane.modelToView(position).getLocation();
		} catch (BadLocationException e2) {
			e2.printStackTrace();
			return;
		}
		int start = Math.max(0, position - 1);
		System.out.println("start : "+start);
		while (start > 0) {
			if (!Character.isWhitespace(text.charAt(start))) {
				start--;
			} else {
				System.out.println("increment");
				start++;
				break;
			}
		}
		System.out.println("start : "+start);
		if (start > position) {
			System.out.println("return!");
			return;
		}
		String subWord = text.substring(start, position+count);
		subWord = subWord.replace("\n","");
		subWord = subWord.replaceAll(".*\\s+","");
		System.out.println(subWord);
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
		
	}
}