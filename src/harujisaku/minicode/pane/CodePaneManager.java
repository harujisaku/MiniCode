package harujisaku.minicode.pane;

import harujisaku.minicode.pane.*;
import harujisaku.minicode.panel.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

import javax.swing.JTabbedPane;

public class CodePaneManager extends JTabbedPane{
	List<JCodePanel> codePanelList = new ArrayList<JCodePanel>();
	public CodePaneManager(){
		
	}
	
	public void add(File file){
		if (file!=null) {		
			codePanelList.add(new JCodePanel(4,file));
			addTab(file.getName(),codePanelList.get(codePanelList.size()-1));
		}
	}
	
	public void add(){
		codePanelList.add(new JCodePanel());
		addTab("untitled",codePanelList.get(codePanelList.size()-1));
	}
	
	public void add(int l){
		codePanelList.add(new JCodePanel(l));
		addTab("untitled",codePanelList.get(codePanelList.size()-1));
	}
	
	public void add(int i,File file){
		if (file!=null) {
			codePanelList.add(new JCodePanel(i,file));
			addTab(file.getName(),codePanelList.get(codePanelList.size()-1));
		}
	}
		
	public void remove(int index){
		remove(index);
		codePanelList.remove(index);
	}
	
	public List<JCodePanel> getCodePaneList(){
		return codePanelList;
	}
	
	public JCodePanel get(int index){
		return (JCodePanel)codePanelList.get(index);
	}
	
	public void updateTabTitle(){
		for(int i=0,len=codePanelList.size();i<len;i++){
			System.out.println(codePanelList.get(i).getCodePane().getFileName());
			setTitleAt(i,codePanelList.get(i).getCodePane().getFileName());
		}
	}
}