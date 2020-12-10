package harujisaku.minicode.panel;

import harujisaku.minicode.*;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.KeyboardFocusManager;
import java.util.Collections;

public class JConsolePanel extends JPanel{
	JButton runComandButton = new JButton("run");
	JConsolePane console;
	JScrollPane scrollPane = new JScrollPane();
	JTextField textfield = new JTextField();
	JPanel panel = new JPanel();
	public JConsolePanel(){
		setLayout(new BorderLayout());
		console = new JConsolePane();
		scrollPane.setViewportView(console);
		scrollPane.setPreferredSize(new Dimension(500,100));
		textfield.setPreferredSize(new Dimension(400,25));
		textfield.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,Collections.EMPTY_SET );
		runComandButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				console.runComand(textfield.getText());
				textfield.setText("");
			}
		});
		textfield.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				console.runComand(textfield.getText());
				textfield.setText("");
			}
		});
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			public void adjustmentValueChanged(AdjustmentEvent e) {
			}
		});
		panel.setLayout(new BorderLayout());
		panel.add(textfield,BorderLayout.CENTER);
		panel.add(runComandButton,BorderLayout.EAST);
		add(scrollPane,BorderLayout.CENTER);
		add(panel,BorderLayout.NORTH);
	}
}