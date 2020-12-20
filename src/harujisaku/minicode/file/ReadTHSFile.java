package harujisaku.minicode.file;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.awt.Color;

import harujisaku.minicode.textedit.*;
import harujisaku.minicode.theme.*;

public class ReadTHSFile {
	public static void readFile(File file){
		Properties prop = new Properties();
		try {
			InputStream is = new FileInputStream(file);
			prop.loadFromXML(is);
			for (Entry<Object,Object> entry :prop.entrySet() ) {
				if (entry.getKey().equals("GRAY")) {
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.GRAY=new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("WHITE")) {
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.WHITE=new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("BLUE")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.BLUE = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("GREEN")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.RED = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("PINK")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.PINK = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("LIGHT_GRAY")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.BLACK = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("MAGENTA")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.MAGENTA = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("YELLOW")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.YELLOW = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("CYAN")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.CYAN = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("DARK_GRAY")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.DARK_GRAY = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("BLACK")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.BLACK = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}else if (entry.getKey().equals("ORANGE")){
					String[] str = ((String)entry.getValue()).split(",");
					ThemeColor.ORANGE = new Color(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}