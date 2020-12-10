package harujisaku.minicode.file;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class FileManager {
	public static String LINE_SEPARATOR = "\r\n";
	private static File countDir;
	public FileManager(File countDir){
		if (countDir.isFile()) {
			countDir= new File(countDir.getPath());
		}
		this.countDir=countDir;
	}
	
	public static boolean save(File fileName,String text){
		if (fileName==null) {
			JFileChooser saveFile = new JFileChooser();
			int selected =saveFile.showSaveDialog(null);
			System.out.println(selected);
			if (selected==JFileChooser.APPROVE_OPTION) {
				fileName = saveFile.getSelectedFile();
			}
		}
		if (!isCanWriteFile(fileName)) {
			return false;
		}
		try {
			System.out.println(text);
			FileWriter fr = new FileWriter(fileName);
			fr.write(text.replaceAll("\n",LINE_SEPARATOR));
			fr.close();
			System.out.println("aa");
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
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
}
