package harujisaku.minicode.pane;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* コマンドプロンプトでコマンドを実行しそのコマンドの実行結果をtextAreaに出力することができるクラスです.
*/

public class JConsolePane extends JTextArea{
	PrintWriter writer;
	Process p;
	/**
	* デフォルトコンストラクタ
	*/
	public JConsolePane(){
		setEditable(false);
		startShell("cmd");
	}
	
	/**
	* シェルを開始する
	* @param shellName シェルの名前,cmd,bash等
	*/
	
	public void startShell(String shellName){
		try {
			ProcessBuilder builder = new ProcessBuilder(shellName);
			builder.redirectErrorStream(true);
			p=builder.start();
			writer = new PrintWriter(p.getOutputStream(),true);
			new Thread(new Runnable(){
				public void run(){
					try {
						while(true){
							BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(),"CP932"));
							String line;
							while((line=reader.readLine())!=null){
								append(line+"\n");
							}
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	* コマンドを実行します
	* @param comand 実行するコマンド
	*/
	
	public void runComand(String comand){
		writer.println(comand);
	}
	
	/**
	* キーを送ります
	* @param key 送るキーのchar
	*/
	
	public void sendKey(char key){
		writer.print(key);
	}
}