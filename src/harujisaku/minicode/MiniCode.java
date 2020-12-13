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

/**
* エントリーポイントがあるメインクラスです.
*/

public class MiniCode{
	/**
	* 空白文字
	*/
	public static String WHITE_SPACE_REGEX = "[/\\\\(\\)\"':,.;<>~\\!@#$%\\^&*\\|\\+=\\[\\]\\{\\}`\\?\\-…]";
	/**
	* 繰り返す文字
	*/
	public static String REPEAT_LETTER_REGEX = "[\\[\\]\\(\\)\\{\\}\"'`]";
	MainFrame mainFrame = new MainFrame(this);
	ReplaceFrame replaceFrame = new ReplaceFrame();
	MiniCode(){
	}

	/**
	エントリーポイント.
	* {@link myMain(String[])}に処理が移る.
	* @param args コマンドライン引数
	*/

	public static void main(String[] args) {
		MiniCode minicode = new MiniCode();
		minicode.myMain(args);
	}
	
	/**
	* 非staticエントリーポイント
	* @param args コマンドライン引数
	*/
	
	public void myMain(String[] args){
	}
	
	/**
	* メインフレームを取得します.
	* @return 使用しているMainFrame
	*/
	
	public MainFrame getMainFrame(){
		return mainFrame;
	}
	
	/**
	* 検索置換ウィンドウのフレームを取得します.
	* @return ReplaceFrame
	*/
	
	public ReplaceFrame getReplaceFrame(){
		return replaceFrame;
	}

}