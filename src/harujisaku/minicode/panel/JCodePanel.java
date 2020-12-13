package harujisaku.minicode.panel;

import harujisaku.minicode.pane.JCodePane;

import java.io.File;

import javax.swing.JScrollPane;
import java.awt.Dimension;

/**
* コードを表示するためのスクロール付きのペインです.
* @author harujisaku
* @version 1.0
* @since 1.0
*/

public class JCodePanel extends JScrollPane{
	private JCodePane jcodePane;
	
	/**
	* デフォルトコンストラクタ.
	* タブ幅が4の{@link JCodePane}を作成します.
	*/
	
	public JCodePanel(){
		jcodePane  = new JCodePane(4);
		setViewportView(jcodePane);
		setPreferredSize(new Dimension(500,500));
	}
	
	/**
	* タブサイズを指定して作成します.
	* @param tabSize タブのサイズ
	*/
	
	public JCodePanel(int tabSize){
		jcodePane  = new JCodePane(tabSize);
		setViewportView(jcodePane);
		setPreferredSize(new Dimension(500,500));
	}
	
	/**
	* タブサイズと読み込むファイルを指定して作成します.
	* @param tabSize タブのサイズ
	* @param file 読み込むファイル
	*/
	
	public JCodePanel(int tabSize,File file){
		jcodePane = new JCodePane(tabSize,file);
		setViewportView(jcodePane);
		setPreferredSize(new Dimension(500,500));
	}
	
	/**
	* {@link JCodePane}を取得します.
	* @return JCodePane
	*/
	
	public JCodePane getCodePane(){
		return jcodePane;
	}
}
