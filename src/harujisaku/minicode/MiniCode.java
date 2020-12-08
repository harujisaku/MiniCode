package harujisaku.minicode;

import harujisaku.minicode.panel.JCodePanel;

import java.util.regex.*;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;

public class MiniCode extends JFrame{
	public static String WHITE_SPACE_REGEX = "[/\\\\(\\)\"':,.;<>~\\!@#$%\\^&*\\|\\+=\\[\\]\\{\\}`\\?\\-…]";
	public static String REPEAT_LETTER_REGEX = "[\\[\\]\\(\\)\\{\\}\"'`]";
	String title = "untitled";
	JCodePanel textPanel = new JCodePanel(4);
	
	
	MiniCode(){
		setTitle(title);
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(textPanel,BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		MiniCode minicode = new MiniCode();
		minicode.myMain(args);
	}
	
	public void myMain(String[] args){
		String lafClassName = UIManager.getSystemLookAndFeelClassName();
		try{
			UIManager.setLookAndFeel(lafClassName);
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}