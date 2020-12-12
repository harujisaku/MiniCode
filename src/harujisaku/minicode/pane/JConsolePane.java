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

public class JConsolePane extends JTextArea{
	public PrintWriter writer;
	Process p;
	public JConsolePane(){
		setEditable(false);
		startShell("cmd");
	}
	
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
	
	public void runComand(String comand){
		writer.println(comand);
	}
	
	public void sendKey(char key){
		writer.print(key);
	}
}