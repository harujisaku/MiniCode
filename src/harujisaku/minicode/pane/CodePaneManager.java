package harujisaku.minicode.pane;

import harujisaku.minicode.pane.*;
import harujisaku.minicode.panel.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
* たくさんのJCodePaneをListで管理するクラスです.
*/

public class CodePaneManager extends DraggableTabbedPane{
	List<JCodePanel> codePanelList = new ArrayList<JCodePanel>();
	public static JCodePanel selectingCodePanel = null;
	/**
	* デフォルトコンストラクタ
	*/
	
	public CodePaneManager(){
		addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				if (getSelectedIndex()>=0) {
					selectingCodePanel = get(getSelectedIndex());
				}else{
					selectingCodePanel=null;
				}
			}
		});
	}
	
	/**
	* 渡されたファイルでJCodePanelを追加します
	* @param file 読み込むファイル
	*/
	
	public void add(File file){
		if (file!=null) {		
			codePanelList.add(new JCodePanel(4,file));
			addTab(file.getName(),codePanelList.get(codePanelList.size()-1));
		}
	}
	
	/**
	* JCodePanelを追加します
	*/
	
	public void add(){
		codePanelList.add(new JCodePanel());
		addTab("untitled",codePanelList.get(codePanelList.size()-1));
	}
	
	/**
	* 渡されたサイズでのJCodePanelを追加します
	* @param l タブサイズ
	*/
	
	public void add(int l){
		codePanelList.add(new JCodePanel(l));
		addTab("untitled",codePanelList.get(codePanelList.size()-1));
	}
	
	/**
	* 渡されたサイズとファイルでJCodePanelを追加します
	* @param i タブのサイズ
	* @param file 読み込むファイル
	*/
	
	public void add(int i,File file){
		if (file!=null) {
			codePanelList.add(new JCodePanel(i,file));
			addTab(file.getName(),codePanelList.get(codePanelList.size()-1));
		}
	}
	
	/**
	* 渡されたindexでJCodePanelを削除します
	* @param index 削除するJCodePanelのindex
	*/
		
	public void remove(int index){
		removeTabAt(index);
		codePanelList.remove(index);
	}
	
	/**
	* JCodePanelをListで取得します
	* @return JCodePanelのList
	*/
	
	public List<JCodePanel> getCodePaneList(){
		return codePanelList;
	}
	
	/**
	* 渡されたindexでJCodePanelを取得します
	* @param index 取得するindex
	* @return Listのindex番目JCodePanel
	*/
	
	public JCodePanel get(int index){
		return (JCodePanel)codePanelList.get(index);
	}
	
	/**
	* タブのタイトルをアップデートします
	*/
	
	public void updateTabTitle(){
		for(int i=0,len=codePanelList.size();i<len;i++){
			setTitleAt(i,codePanelList.get(i).getCodePane().getFileName());
		}
	}

	public void addTab(String title,final JComponent content){
		JPanel tab = new JPanel(new BorderLayout());
		tab.setOpaque(false);
		JLabel label = new JLabel(title);
		label.setBorder(BorderFactory.createEmptyBorder(0,0,0,4));
		JButton button = new JButton("x");
		button.setMargin(new Insets(0,0,0,0));
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				remove(getSelectedIndex());
			}
		});
		tab.add(label,BorderLayout.WEST);
		tab.add(button,BorderLayout.EAST);
		tab.setBorder(BorderFactory.createEmptyBorder(2,1,1,1));
		super.addTab(null,content);
		setTabComponentAt(getTabCount()-1,tab);
	}
}
