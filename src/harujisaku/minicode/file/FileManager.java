package harujisaku.minicode.file;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

/**
* ファイル管理をするクラスです.
* staticなので呼び出すだけで大丈夫です
* @author harujisaku
* @version 1.0
* @since 1.0
*/

public class FileManager {
	
	/**
	デフォルトの改行コード
	*/
	
	public static String LINE_SEPARATOR = "\r\n";
	private static File countDir;
	
	/**
	* デフォルトコンストラクタ.
	* @param countDir カレントディレクトリ
	*/
	public FileManager(File countDir){
		if (countDir.isFile()) {
			countDir= new File(countDir.getPath());
		}
		this.countDir=countDir;
	}
	
	/**
	* ファイルを保存します.
	* @param fileName 保存時のパスを設定します.null担っている場合ダイアログを出します.
	* @param text 保存する文字列を設定します.
	* @return 保存されたファイル
	*/
	
	public static File save(File fileName,String text){
		if (fileName==null) {
			JFileChooser saveFile = new JFileChooser();
			int selected =saveFile.showSaveDialog(null);
			System.out.println(selected);
			if (selected==JFileChooser.APPROVE_OPTION) {
				fileName = saveFile.getSelectedFile();
			}
		}
		if (!isCanWriteFile(fileName)) {
		}
		try {
			System.out.println(text);
			FileWriter fr = new FileWriter(fileName);
			fr.write(text.replaceAll("\n",LINE_SEPARATOR));
			fr.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	/**
	* 渡されたファイルを読み込みその中の文字列を返します
	* @param fileName 読み込むファイル
	* @return ファイルに保存されていた文字列
	*/
	
	public static String loadFile(File fileName){
		if (fileName==null) {
			return "";
		}
		try {
			if (isCanReadFile(fileName)) {
				BufferedReader br = new BufferedReader(new InputStreamReader(new  FileInputStream(fileName),"UTF-8"));
				StringBuffer str = new StringBuffer();
				String line;
				while ((line=br.readLine())!=null) {
					str.append(line+"\n");
				}
				br.close();
				return str.toString();
			}else{
				System.out.println("file not found");
				return "error";
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	/**
	* ファイルを開くダイアログを出して開くファイルを選択させます.
	* 何も選択されなかった場合はnullが返されます
	* @return 読み込むファイル
	*/
	
	public static File load(){
		JFileChooser loadFile = new JFileChooser();
		int selected = loadFile.showOpenDialog(null);
		if(selected==JFileChooser.APPROVE_OPTION){
			return loadFile.getSelectedFile();
		}
		return null;
	}
	
	private static boolean isCanWriteFile(File file){
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
			if (file.isFile()&&file.canWrite()) {
				return true;
			}
		return false;
	}
	
	private static boolean isCanReadFile(File file){
		if (file.exists()) {
			if (file.isFile()&&file.canRead()) {
				return true;
			}
		}
		return false;
	}
}
