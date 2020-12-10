package harujisaku.minicode.panel;

import harujisaku.minicode.*;
import harujisaku.minicode.panel.*;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class MainPanel extends JPanel {
	public MainPanel(){
		setLayout(new BorderLayout());
		JConsolePanel console = new JConsolePanel();
		JCodePanel code = new JCodePanel(4);
		JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitpane.setResizeWeight(1.0);
		splitpane.setBottomComponent(console);
		splitpane.setTopComponent(code);
		add(splitpane,BorderLayout.CENTER);
	}
	
	
}