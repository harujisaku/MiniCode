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
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
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
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
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
	
	public void add(int i,int index){
		System.out.println(index);
		codePanelList.add(index,new JCodePanel(i));
		insertTab("untitled",null,codePanelList.get(index),null,index);
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
	
	public void add(int i,int index,File file){
		if (file!=null) {
			codePanelList.add(index,new JCodePanel(i,file));
			insertTab(file.getName(),null,codePanelList.get(index),null,index);
		}
	}
	
	/**
	* 渡されたindexでJCodePanelを削除します
	* @param index 削除するJCodePanelのindex
	*/

	public int removePane(int index){
		if (!codePanelList.get(index).getCodePane().isSaved()) {
			JFrame frame = new JFrame();
			int option =JOptionPane.showConfirmDialog(frame,"保存しますか？"+codePanelList.get(index).getCodePane().getFileName());
			if (option==JOptionPane.YES_OPTION) {
				codePanelList.get(index).getCodePane().save();
			}else if (option==JOptionPane.NO_OPTION) {
				
			}else if (option==JOptionPane.CANCEL_OPTION) {
				System.out.println("canceled");
				return 1;
			}
		}
		removeTabAt(index);
		codePanelList.remove(index);
		return 0;
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
				removePane(getSelectedIndex());
			}
		});
		tab.add(label,BorderLayout.WEST);
		tab.add(button,BorderLayout.EAST);
		tab.setBorder(BorderFactory.createEmptyBorder(2,1,1,1));
		super.addTab(null,content);
		setTabComponentAt(getTabCount()-1,tab);
	}
	
	public void insertTab(String title,javax.swing.Icon icon,JComponent content,String tip,int index){
		JPanel tab = new JPanel(new BorderLayout());
		tab.setOpaque(false);
		JLabel label = new JLabel(title);
		label.setBorder(BorderFactory.createEmptyBorder(0,0,0,4));
		JButton button = new JButton("x");
		button.setMargin(new Insets(0,0,0,0));
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				removePane(getSelectedIndex());
			}
		});
		tab.add(label,BorderLayout.WEST);
		tab.add(button,BorderLayout.EAST);
		tab.setBorder(BorderFactory.createEmptyBorder(2,1,1,1));
		try {
			super.insertTab(null,null,content,null,index);
		} catch(Exception e) {
			super.addTab(null,content);
		}
		setTabComponentAt(index,tab);
	}
}
