package harujisaku.minicode;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar{
	public Menu(MiniCode m){
		JMenu file = new JMenu("ファイル(F)");
		JMenu edit = new JMenu("編集(E)");
		JMenuItem save = new JMenuItem("保存(S)");
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		save.setMnemonic(KeyEvent.VK_S);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				m.save();
			}
		});
		file.add(save);
		add(file);
		add(edit);
	}
}