package harujisaku.minicode.autocomplete;

import harujisaku.minicode.autocomplete.suggest.*;

import java.awt.Point;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.InputEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

import javax.swing.text.BadLocationException;

public class AutoCompletePanel extends JPopupMenu{
	AbstractSuggest suggestString = new JavaSuggest();
 	private JTextPane textpane;
	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
	private StringBuffer word=new StringBuffer();
	private boolean isShow=false,wasShow=false;
	public  AutoCompletePanel(JTextPane textpane){
		super();
		this.textpane=textpane;
		removeAll();
		setOpaque(false);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFocusable(false);
		list.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount()==2) {
					final int position = textpane.getCaretPosition();
					try {
						textpane.getDocument().insertString(position,getSelectedString().substring(word.length()),null);
					} catch(BadLocationException e2) {
						e2.printStackTrace();
					}
					word.delete(0,word.length());
					hidePanel();
		}}});
		textpane.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e){
				final int position = textpane.getCaretPosition();
				if (e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_DOWN) {
					return;
				}
				if (Character.isWhitespace(e.getKeyChar())) {
					if (e.getKeyChar()=='\n'&&isShow) {
						try {
							textpane.getDocument().insertString(position,getSelectedString().substring(word.length()),null);
						} catch(BadLocationException e2) {
							e2.printStackTrace();
						}
						e.consume();
						hidePanel();
						word.delete(0,word.length());
					}else if(e.getKeyChar()==' '&&!isShow&&(e.getModifiersEx()&InputEvent.CTRL_DOWN_MASK)!=0){
						e.consume();
						SwingUtilities.invokeLater(new Runnable(){
							@Override
							public void run(){
								if (word.length()==0) {
									word.append(getWord(position-1));
								}
								if (word.length()>=2) {
									model.clear();
									if (!updateWordList()) {
										hidePanel();
										return;
									}
									isShow=true;
									wasShow=false;
									showPanel();
								}
							}
						});
					}else{
						hidePanel();
						word.delete(0,word.length());
					}
					return;
				}else if (Character.isLetterOrDigit(e.getKeyChar())) {
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run(){
							if (word.length()==0) {
								word.append(getWord(position-1));
							}
							word.append(e.getKeyChar());
							if (word.length()>=2) {
								model.clear();
								if (!updateWordList()) {
									hidePanel();
									return;
								}
								isShow=true;
								wasShow=false;
								showPanel();
							}
						}
					});
				}else{
					word.delete(0,word.length());
					hidePanel();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e){
			}
			
			@Override
			public void keyReleased(KeyEvent e){
				if (e.getKeyCode()==KeyEvent.VK_DOWN&&isShow) {
					list.setSelectedIndex(Math.min(list.getModel().getSize()-1,list.getSelectedIndex()+1));
					e.consume();
				}else if(e.getKeyCode()==KeyEvent.VK_UP&&isShow){
					list.setSelectedIndex(Math.max(list.getSelectedIndex()-1,0));
					e.consume();
		}}});
	}
	
	private boolean updateWordList(){
		final String[] words = suggestString.search(word.toString());
		if (words.length<1) {
			return false;
		}
		for (final String str : words) {
			model.addElement(str);
		}
		return true;
	}
	
	private void showPanel(){
		final int position = textpane.getCaretPosition();
		final int returnCount = getTextRow(textpane.getText(),position);
		try {
			final Point location = textpane.modelToView(position).getLocation();
			setVisible(true);
			setOpaque(true);
			add(list);
			show(textpane,location.x,textpane.getBaseline(0,0)+location.y+15);
			list.setSelectedIndex(0);
			textpane.setCaretPosition(position);
		} catch(BadLocationException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				textpane.requestFocusInWindow();
			}
		});
	}
	
	private int getTextRow(String text){
		return getTextRow(text,text.length());
	}
	
	private int getTextRow(String text,int max){
		int count=0;
		for (int i=0;i>max;i++) {
			if (text.charAt(i)=='\n') {
				count++;
			}
		}
		return count;
	}
	
	private String getWord(int index){
		if (index<0) {
			return "";
		}
		final String text = textpane.getText();
		char word;
		StringBuffer br = new StringBuffer();
		while (!Character.isWhitespace(word=text.charAt(index))) {
			index--;
			br.append(word);
			if (index<0) {
				br.reverse();
				return br.toString();
			}
		}
		br.reverse();
		return br.toString();
	}
	
	private String getSelectedString(){
		if (list.getSelectedValue()!=null) {
			return (String)list.getSelectedValue();
		}
		return "";
	}
	
	private void hidePanel(){
		model.clear();
		setVisible(false);
		if (isShow) {
			isShow=false;
			wasShow=true;
		}else{
			wasShow=false;
		}
	}
	
	public boolean isShow(){
		return isShow;
	}
	
	public boolean wasShow(){
		return wasShow;
	}
}