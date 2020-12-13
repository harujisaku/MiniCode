package harujisaku.minicode.frame;

import harujisaku.minicode.replace.*;
import harujisaku.minicode.pane.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.text.JTextComponent;

/**
* 検索・置換クラス
* @author harujisaku
* @version 1.0
* @since 1.0
*/

public class ReplaceFrame extends JFrame{
	/**
	* デフォルトコンストラクタ.
	* ウィンドウが作成されますが表示されませんので手動で {@link java.awt.Window#setVisible(boolean)} してください。
	*/
	
	JTextComponent textComponent;
	FindReplace findReplace;
	String regex="";
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
		find.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (textComponent!=(JTextComponent)CodePaneManager.selectingCodePanel.getCodePane()) {
					textComponent=CodePaneManager.selectingCodePanel.getCodePane();
					findReplace = new FindReplace(textComponent);
					System.out.println("new");
				}
				String testRegex = findText.getText();
				if (!regex.equals(testRegex)) {
					findReplace.setFindRegex(testRegex);
					regex=testRegex;
					System.out.println("new");
				}
				Find find = findReplace.find();
				textComponent.requestFocus();
				System.out.println(find.getStart());
				System.out.println(find.getEnd());
				textComponent.select(find.getStart(),find.getEnd());
			}
		});
	}
}