package harujisaku.minicode.frame;

import harujisaku.minicode.pane.*;
import harujisaku.minicode.frame.*;
import harujisaku.minicode.textedit.*;
import harujisaku.minicode.file.*;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

/**
* メニューバーを管理するクラスです.
* @author harujisaku
* @version 1.0
* @since 1.0
*/

public class Menu extends JMenuBar{
	
	/**
	デフォルトコンストラクタ.
	@param m メニューを表示しているJFrame
	*/
	
	public Menu(MainFrame m){
		JMenu file = new JMenu("ファイル(F)");
		JMenu edit = new JMenu("編集(E)");
		JMenu view = new JMenu("表示(V)");
		JMenu tabSize = new JMenu("タブ幅");
		JMenu autoComplete = new JMenu("自動補完");
		JMenu syntaxHighlight = new JMenu("シンタックスハイライト");
		JMenu lookAndFeel = new JMenu("LookAndFeel");
		JMenuItem newFile = new JMenuItem("新規");
		JMenuItem save = new JMenuItem("保存");
		JMenuItem saveAs = new JMenuItem("名前を付けて保存");
		JMenuItem open = new JMenuItem("開く");
		JMenuItem exit = new JMenuItem("終了");
		JMenuItem findReplace = new JMenuItem("検索/置換");
		JRadioButtonMenuItem tab2 = new JRadioButtonMenuItem("2");
		JRadioButtonMenuItem tab4 = new JRadioButtonMenuItem("4");
		JRadioButtonMenuItem tab8 = new JRadioButtonMenuItem("8");
		LoadConfigFolder lcf = new LoadConfigFolder() ;
		
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		view.setMnemonic(KeyEvent.VK_V);
		save.setMnemonic(KeyEvent.VK_S);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));
		saveAs.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_DOWN_MASK));
		findReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_DOWN_MASK));
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
		newFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (m.codePaneManager.getSelectedIndex()!=-1) {
					m.codePaneManager.add(4,m.codePaneManager.getSelectedIndex());
				}else{
					m.codePaneManager.add(4,0);
				}
			}
		});
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (CodePaneManager.selectingCodePanel!=null) {
					CodePaneManager.selectingCodePanel.getCodePane().save();
				}
				m.getCodePaneManager().updateTabTitle();
			}
		});
		saveAs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (CodePaneManager.selectingCodePanel!=null) {
					CodePaneManager.selectingCodePanel.getCodePane().saveAs();
				}
				m.getCodePaneManager().updateTabTitle();
			}
		});
		open.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				m.getCodePaneManager().add(FileManager.load());
				m.getCodePaneManager().setSelectedIndex(m.getCodePaneManager().getTabCount()-1);
			}
		});
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				m.dispose();
				System.exit(0);
			}
		});
		findReplace.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (m.getMiniCode().getReplaceFrame().isVisible()) {
					m.getMiniCode().getReplaceFrame().setVisible(false);
				}else{
					m.getMiniCode().getReplaceFrame().setVisible(true);
				}
			}
		});
		tab2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (CodePaneManager.selectingCodePanel!=null) {
					CodePaneManager.selectingCodePanel.getCodePane().setTabSize(2);
				}
			}
		});
		tab4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (CodePaneManager.selectingCodePanel!=null) {
					CodePaneManager.selectingCodePanel.getCodePane().setTabSize(4);
				}
			}
		});
		tab8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (CodePaneManager.selectingCodePanel!=null) {
					CodePaneManager.selectingCodePanel.getCodePane().setTabSize(8);
				}
			}
		});

		tab4.setSelected(true);
		
		ButtonGroup tabSizeGroup = new ButtonGroup();
		ButtonGroup autoCompleteGroup = new ButtonGroup();
		ButtonGroup syntaxHighlightGroup = new ButtonGroup();
		ButtonGroup lookAndFeelGroup = new ButtonGroup();
		
		tabSizeGroup.add(tab2);
		tabSizeGroup.add(tab4);
		tabSizeGroup.add(tab8);
	
		
		JRadioButtonMenuItem[] highlight = lcf.LoadHighlightFolder();
		for (JRadioButtonMenuItem menuItem :highlight ) {
			syntaxHighlight.add(menuItem);
			syntaxHighlightGroup.add(menuItem);
		}
		JRadioButtonMenuItem[] autoCompletes = lcf.LoadAutoCompleteFolder();
		for (JRadioButtonMenuItem menuItem :autoCompletes ) {
			autoComplete.add(menuItem);
			autoCompleteGroup.add(menuItem);
		}
		UIManager.LookAndFeelInfo infos[] = UIManager.getInstalledLookAndFeels();
		for ( UIManager.LookAndFeelInfo info :infos ) {
			JRadioButtonMenuItem lookAndFeelMenu = new JRadioButtonMenuItem(info.getName());
			lookAndFeel.add(lookAndFeelMenu);
			lookAndFeelGroup.add(lookAndFeelMenu);
			lookAndFeelMenu.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						UIManager.setLookAndFeel(info.getClassName());
						SwingUtilities.updateComponentTreeUI(m);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
		}
		file.add(newFile);
		file.add(save);
		file.add(saveAs);
		file.add(open);
		file.add(exit);
		edit.add(findReplace);
		tabSize.add(tab2);
		tabSize.add(tab4);
		tabSize.add(tab8);
		view.add(tabSize);
		edit.add(autoComplete);
		view.add(syntaxHighlight);
		view.add(lookAndFeel);
		add(file);
		add(edit);
		add(view);
	}
}