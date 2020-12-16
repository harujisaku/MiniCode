package harujisaku.minicode.frame;

import harujisaku.minicode.pane.*;
import harujisaku.minicode.panel.*;
import harujisaku.minicode.*;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
* メインのフレームです.
*/

public class MainFrame extends JFrame {
	String title = "untitled";
	CodePaneManager codePaneManager = new CodePaneManager();
	MainPanel mainPanel = new MainPanel(codePaneManager);
	MiniCode m;
	
	/**
	* デフォルトコンストラクタ.
	* @param m 設定元のMiniCode
	*/
	
	public MainFrame(MiniCode m){
		setTitle(title);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(mainPanel,BorderLayout.CENTER);
		Menu menu = new Menu(this);
		setJMenuBar(menu);
		pack();
		this.m=m;
		JFrame a = this;
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				setVisible(true);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					SwingUtilities.updateComponentTreeUI(a);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		codePaneManager.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				if (codePaneManager.getSelectedIndex()>=0) {
					setTitle(codePaneManager.getTitleAt(codePaneManager.getSelectedIndex()));
				}else{
					setTitle("no file");
				}
			}
		});
	}
	
	/**
	* メインパネルを取得します.
	* @return メインパネル
	*/
	
	public MainPanel getMainPanel(){
		return mainPanel;
	}
	
	/**
	* CodePaneManagerを取得します.
	* @return CodePaneManager
	*/
	
	public CodePaneManager getCodePaneManager(){
		return codePaneManager;
	}
	
	/**
	* MiniCodeを取得します. 
	* @return MiniCode. 
	*/
	
	public MiniCode getMiniCode(){
		return m;
	}
}