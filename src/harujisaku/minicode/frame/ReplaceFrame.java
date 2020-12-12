package harujisaku.minicode.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ReplaceFrame extends JFrame{
	public ReplaceFrame(){
		setTitle("Replace");
		setLocationRelativeTo(null);
		setSize(600,150);
		setLayout(null);
		JTextField findText = new JTextField(50);
		JTextField replaceText = new JTextField(50);
		JButton find = new JButton("Find");
		JButton replace = new JButton("Replace");
		findText.setBounds(10,10,440,40);
		replaceText.setBounds(10,60,440,40);
		find.setBounds(450,10,100,40);
		replace.setBounds(450,60,100,40);
		add(findText);
		add(find);
		add(replaceText);
		add(replace);
	}
}