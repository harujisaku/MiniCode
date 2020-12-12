package harujisaku.minicode.frame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ReplaceFrame extends JFrame{
	public ReplaceFrame(){
		setTitle("Replace");
		setLocationRelativeTo(null);
		setSize(500,100);
		setLayout(new FlowLayout());
		JTextField findText = new JTextField(50);
		JTextField replaceText = new JTextField(50);
		JButton find = new JButton("Find");
		JButton replace = new JButton("Replace");
		add(findText);
		add(find);
		add(replaceText);
		add(replace);
		pack();
	}
}