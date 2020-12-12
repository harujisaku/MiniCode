package harujisaku.minicode.pane;

import harujisaku.minicode.pane.*;
import harujisaku.minicode.panel.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

public class CodePaneManager {
	List<JCodePanel> codePanelList = new ArrayList<JCodePanel>();
	public CodePaneManager(){
	}
	
	public void add(File file){
		codePanelList.add(new JCodePanel(4,file));
	}
	
	public void add(){
		codePanelList.add(new JCodePanel());
	}
	
	public void add(int l){
		codePanelList.add(new JCodePanel(l));
	}
	
	public void add(int i,File file){
		codePanelList.add(new JCodePanel(i,file));
	}
	
	public void remove(JCodePanel jcl){
		codePanelList.remove(jcl);
	}
	
	public void remove(int index){
		codePanelList.remove(index);
	}
	
	public List<JCodePanel> getCodePaneList(){
		return codePanelList;
	}
	
	public JCodePanel get(int index){
		return (JCodePanel)codePanelList.get(index);
	}
}