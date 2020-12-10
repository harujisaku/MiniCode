package harujisaku.minicode;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar{
	public Menu(){
		JMenu file = new JMenu("ファイル(F)");
		JMenu edit = new JMenu("編集(E)");
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		add(file);
		add(edit);
	}
}