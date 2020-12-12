package harujisaku.minicode;

import harujisaku.minicode.panel.*;
import harujisaku.minicode.pane.*;
import harujisaku.minicode.frame.*;

import java.util.regex.*;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;

public class MiniCode extends JFrame{
	public static String WHITE_SPACE_REGEX = "[/\\\\(\\)\"':,.;<>~\\!@#$%\\^&*\\|\\+=\\[\\]\\{\\}`\\?\\-…]";
	public static String REPEAT_LETTER_REGEX = "[\\[\\]\\(\\)\\{\\}\"'`]";
	MainFrame mainFrame = new MainFrame(this);
	ReplaceFrame replaceFrame = new ReplaceFrame();
	MiniCode(){
	}

	public static void main(String[] args) {
		MiniCode minicode = new MiniCode();
		minicode.myMain(args);
	}
	
	public void myMain(String[] args){
	}
	
	public MainFrame getMainFrame(){
		return mainFrame;
	}
	
	public ReplaceFrame getReplaceFrame(){
		return replaceFrame;
	}

}