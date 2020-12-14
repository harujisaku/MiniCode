package harujisaku.minicode.pane;

import harujisaku.minicode.textedit.*;
import harujisaku.minicode.file.*;

import java.io.File;

import java.awt.FontMetrics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import javax.swing.text.DefaultEditorKit;
import javax.swing.text.TabStop;
import javax.swing.text.TabSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import javax.swing.text.BadLocationException;

/**
* コードの表示をするためのクラスです.
* @author harujisaku
* @version 1.0
* @since 1.0
*/

public class JCodePane extends JTextPane {
	/**
	タブの長さを保持しておく変数です.
	オートインデントがおかしくなるので基本的には変えないこと
	*/
	protected int tabLength=0;
	private boolean wasAutoCompleteShow=false;
	private File file = null;
	/**
	シンタックスハイライトを保持する変数です.
	*/
	protected Highlight highlight = new Highlight(new TextHighlight(),this);
	
	/**
	* デフォルトコンストラクタ.
	* 自動でタブサイズが4に設定されます.
	*/
	
	public JCodePane(){
		setTabSize(4);
		init();
	}
	
	/**
	* タブサイズ、読み込むファイルを指定して新しく作成します.
	* @param tabSize タブサイズ
	* @param file 読み込むファイル
	* @see harujisaku.minicode.file.FileManager#loadFile(File)
	*/
	
	public JCodePane(int tabSize,File file){
		setTabSize(tabSize);
		this.file=file;
		setText(FileManager.loadFile(file)) ;
		init();
	}
	
	/**
	* タブサイズを指定して作成します.
	* @param tabSize タブサイズ
	*/
	
	public JCodePane(int tabSize){
		setTabSize(tabSize);
		init();
	}
	
	/**
	* シンタックスハイライトを設定します.
	* @param highlightStyle 設定するシンタックスハイライト
	*/
	
	public void setSyntaxHighLight(AbstractHighlight highlightStyle){
		highlight = new Highlight(highlightStyle,this);
	}
	
	private void init(){
		getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty,"\n");
		AutoCompletePanel autoComplete = new AutoCompletePanel(this);
		addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e){
			}

			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if (!autoComplete.wasShow()) {
						final int position = getCaretPosition();
						tabLength = charCount(getText().substring(indexOfHeadOfLine(position-1),indexOfEndOfLine(position)).replace("\n",""),'\t');
					}
					wasAutoCompleteShow = autoComplete.wasShow();
					System.out.println(tabLength);
				}
				if (Character.isWhitespace(e.getKeyChar())) {
					final int position = getCaretPosition();
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run(){
							highlight.highlight(indexOfHeadOfLine(position-50),indexOfEndOfLine(position+50));
						}
					});
				}else if(e.getKeyCode()==KeyEvent.VK_F1){
					final int position = getCaretPosition();
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run(){
							highlight.highlight();
						}
					});
				}
			}
			@Override
			public void keyReleased(KeyEvent e){
				if (!wasAutoCompleteShow&&e.getKeyCode()==KeyEvent.VK_ENTER) {
					final int position = getCaretPosition();
					try {
						System.out.println(tabLength);
						getDocument().insertString(position,stringRepeat("\t",tabLength),null);
					} catch(BadLocationException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}
	
	/**
	* タブサイズを設定します.
	* @param size タブのサイズ
	*/
	
	public void setTabSize(int size){
		FontMetrics fm=getFontMetrics(getFont());
		TabStop[] tabs = new TabStop[100];
		for (int i=0,len=tabs.length;i<len ;i++ ) {
			tabs[i] = new TabStop((i+1)*fm.charWidth(' ')*size);
		}
		TabSet tabSet = new TabSet(tabs);
		SimpleAttributeSet aset=new SimpleAttributeSet();
		StyleConstants.setTabSet(aset,tabSet);
		getStyledDocument().setParagraphAttributes(0,getDocument().getLength(),aset,false);
	}
	
	/**
	* 渡されたindexが属する行の行頭のindexを返します.
	* @param position 検索を開始するindex
	* @return 行頭のindex
	*/
	
	public int indexOfHeadOfLine(int position){
		if (position<=0) {
			return 0;
		}
		String text=getText();
		int count=position;
		while ((text.charAt(count))!='\n') {
			count--;
			if (count <= 0) {
				return 0;
			}
		}
		return count;
	}
	
	/**
	* 渡されたindexが属する行の行末のindexを返します.
	* @param position 検索を開始するindex
	* @return 行末のindex
	*/
	
	public int indexOfEndOfLine(int position){
		String text=getText();
		if (position>=text.length()) {
			return text.length();
		}
		int count=position,max=text.length()-1;
		while ((text.charAt(count))!='\n') {
			count++;
			if (count >= max) {
				return max;
			}
		}
		return count;
	}
	
	/**
	* 渡されたindexが属する行の文字列を返します.
	* @param position 取得する行を含むindex
	* @return positionを含む行の文字列
	*/
	
	public String getLine(int position){
		String text=getText().substring(indexOfHeadOfLine(position-1),indexOfEndOfLine(position));
		return text;
	}
	
	/**
	* 渡された文字列に渡されたcharが含まれている数を返します.
	* @param text 検索される文字列
	* @param c 検索する文字
	* @return 含まれていた数
	*/
	
	public int charCount(String text,char c){
		int count=0,index=0;
		while(index<text.length()){
			if (text.charAt(count)==c) {
				count++;
			}
			index++;
		}
		return count;
	}
	
	/**
	* 指定した回数渡された文字列を繰り返して加算します.
	* 返される文字列はcount回textを足し算した文字列です。
	* @param text 繰り返す文字列
	* @param count 繰り返す回数
	* @return 加算後の文字列
	*/
	
	public static String stringRepeat(String text,int count){
		StringBuffer br = new StringBuffer();
		for (int i=0;i<count ;i++ ) {
			br.append(text);
		}
		return br.toString();
	}
	
	/**
	* 保存します.
	* @see harujisaku.minicode.file.FileManager#save(File,String)
	*/
	
	public void save(){
		file = FileManager.save(file,getText());
	}
	
	/**
	* 名前を付けて保存します.
	* @see harujisaku.minicode.file.FileManager#save(File,String)
	*/
	
	public void saveAs(){
		file = FileManager.save(null,getText());
		setText(FileManager.loadFile(file));
	}
	
	/**
	* ファイル名を取得します.
	* @return ファイル名
	* @deprecated 別のメソッドに置き換えられました{@link #getFile()}から{@link java.io.File#getName()}を使用してください。
	*/
	
	public String getFileName(){
		return file.getName();
	}
	
	/**
	* {@link java.io.File} を取得します
	* @return File
	*/
	
	public File getFile(){
		return file;
	}
}