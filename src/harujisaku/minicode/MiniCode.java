package harujisaku.minicode;

import harujisaku.minicode.panel.*;
import harujisaku.minicode.pane.*;

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
	String title = "untitled";
	Menu menu ;
	CodePaneManager codePane = new CodePaneManager();
	MainPanel mainPanel = new MainPanel(codePane);
	MiniCode(){
		setTitle(title);
		setSize(500,500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(mainPanel,BorderLayout.CENTER);
		menu = new Menu(this);
		setJMenuBar(menu);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		MiniCode minicode = new MiniCode();
		minicode.myMain(args);
	}
	
	public void myMain(String[] args){
		MiniCode a = this;
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				String lafClassName = UIManager.getSystemLookAndFeelClassName();
				try{
					UIManager.setLookAndFeel(lafClassName);
					SwingUtilities.updateComponentTreeUI(a);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		codePane.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				setTitle(codePane.getTitleAt(codePane.getSelectedIndex()));
			}
		});
	}
	
	public MainPanel getMainPanel(){
		return mainPanel;
	}
	
	public CodePaneManager getCodePaneManager(){
		return codePane;
	}
}