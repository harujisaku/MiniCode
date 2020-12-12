package harujisaku.minicode.panel;

import harujisaku.minicode.pane.JCodePane;

import java.io.File;

import javax.swing.JScrollPane;
import java.awt.Dimension;

public class JCodePanel extends JScrollPane{
	JCodePane jcodePane;
	public JCodePanel(){
		new JCodePanel(4);
	}
	
	public JCodePanel(int tabSize){
		jcodePane  = new JCodePane(tabSize);
		setViewportView(jcodePane);
		setPreferredSize(new Dimension(500,500));
	}
	
	public JCodePanel(int tabSize,File file){
		jcodePane = new JCodePane(tabSize,file);
		setViewportView(jcodePane);
		setPreferredSize(new Dimension(500,500));
	}
	
	public void save(){
		jcodePane.save();
	}
}