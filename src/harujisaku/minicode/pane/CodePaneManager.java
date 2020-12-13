package harujisaku.minicode.pane;

import harujisaku.minicode.pane.*;
import harujisaku.minicode.panel.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

import javax.swing.JTabbedPane;

/**
* たくさんのJCodePaneをListで管理するクラスです.
*/

public class CodePaneManager extends JTabbedPane{
	List<JCodePanel> codePanelList = new ArrayList<JCodePanel>();
	
	/**
	* デフォルトコンストラクタ
	*/
	
	public CodePaneManager(){
		
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
		remove(index);
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
			System.out.println(codePanelList.get(i).getCodePane().getFileName());
			setTitleAt(i,codePanelList.get(i).getCodePane().getFileName());
		}
	}
}
