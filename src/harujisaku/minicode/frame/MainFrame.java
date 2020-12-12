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

public class MainFrame extends JFrame {
	String title = "untitled";
	CodePaneManager codePaneManager = new CodePaneManager();
	MainPanel mainPanel = new MainPanel(codePaneManager);
	MiniCode m;
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
				setTitle(codePaneManager.getTitleAt(codePaneManager.getSelectedIndex()));
			}
		});
	}
	
	public MainPanel getMainPanel(){
		return mainPanel;
	}
	
	public CodePaneManager getCodePaneManager(){
		return codePaneManager;
	}
	
	public MiniCode getMiniCode(){
		return m;
	}
}