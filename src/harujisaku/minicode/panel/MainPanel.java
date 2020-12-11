package harujisaku.minicode.panel;

import harujisaku.minicode.*;
import harujisaku.minicode.panel.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class MainPanel extends JPanel {
	List<JCodePanel> codePanelList = new ArrayList<JCodePanel>();
	public MainPanel(){
		setLayout(new BorderLayout());
		JConsolePanel console = new JConsolePanel();
		codePanelList.add(new JCodePanel(4));
		JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitpane.setResizeWeight(1.0);
		splitpane.setBottomComponent(console);
		splitpane.setTopComponent(codePanelList.get(0));
		add(splitpane,BorderLayout.CENTER);
		splitpane.setDividerLocation(-1);
	}
	
	
}