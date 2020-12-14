package harujisaku.minicode.file;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import java.io.IOException;
import java.io.FileNotFoundException;

import harujisaku.minicode.textedit.*;

public class ReadACSFile {
	public static AutoCompleteLang readFile(File file){
		Properties prop = new Properties();
		String words="",lang="text";
		try {
			InputStream is = new FileInputStream(file);
			prop.loadFromXML(is);
			for (Entry<Object,Object> entry :prop.entrySet() ) {
				if (entry.getKey().equals("words")) {
					words=(String)entry.getValue();
				}else if (entry.getKey().equals("lang")) {
					lang=(String)entry.getValue();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return new AutoCompleteLang(lang,words);
	}
}