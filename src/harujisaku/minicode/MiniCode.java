package harujisaku.minicode;

import java.util.regex.*;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	JCodePane textPane = new JCodePane();
	
	
	MiniCode(){
		setTitle(title);
		getContentPane().setPreferredSize(new Dimension(500,500));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textPane.setBounds(0,0,500,500);
		getContentPane().add(textPane);
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