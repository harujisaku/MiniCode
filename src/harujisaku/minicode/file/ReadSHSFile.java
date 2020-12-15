package harujisaku.minicode.file;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import java.io.IOException;
import java.io.FileNotFoundException;

import harujisaku.minicode.textedit.*;

public class ReadSHSFile {
	public static HighlightLang readFile(File file){
		Properties prop = new Properties();
		String lang="text",WHITE_SPACE_REGEX="",DEFAULT_KEYWORDS="",BLACK_KEYWORDS="",BLUE_KEYWORDS="",CYAN_KEYWORDS="",DARK_GRAY_KEYWORDS="";
		String GRAY_KEYWORDS="",GREEN_KEYWORDS="",LIGHT_GRAY_KEYWORDS="",MAGENTA_KEYWORDS="",ORANGE_KEYWORDS="",PINK_KEYWORDS="",RED_KEYWORDS="",WHITE_KEYWORDS="",YELLOW_KEYWORDS="",quotation="0,0,0";
		try {
			InputStream is = new FileInputStream(file);
			prop.loadFromXML(is);
			for (Entry<Object,Object> entry :prop.entrySet() ) {
				if (entry.getKey().equals("GRAY")) {
					GRAY_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("WHITE")) {
					WHITE_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("BLUE")) {
					BLUE_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("GREEN")) {
					GREEN_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("RED")) {
					RED_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("WHITE_SPACE_REGEX")) {
					WHITE_SPACE_REGEX=(String)entry.getValue();
				}else if (entry.getKey().equals("PINK")) {
					PINK_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("LIGHT_GRAY")) {
					LIGHT_GRAY_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("BLACK")) {
					BLACK_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("MAGENTA")) {
					MAGENTA_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("YELLOW")) {
					YELLOW_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("lang")) {
					lang=(String)entry.getValue();
				}else if (entry.getKey().equals("CYAN")) {
					CYAN_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("DARK_GRAY")) {
					DARK_GRAY_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("quotation")) {
					quotation=(String)entry.getValue();
				}else if (entry.getKey().equals("DEFAULT")) {
					DEFAULT_KEYWORDS=(String)entry.getValue();
				}else if (entry.getKey().equals("ORANGE")) {
					ORANGE_KEYWORDS=(String)entry.getValue();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return new HighlightLang(lang).setWhitespace(WHITE_SPACE_REGEX).setDefault(DEFAULT_KEYWORDS).setBlack(BLACK_KEYWORDS).setBlue(BLUE_KEYWORDS).setCyan(CYAN_KEYWORDS).setDarkGray(DARK_GRAY_KEYWORDS).setGray(GRAY_KEYWORDS).setLightGray(LIGHT_GRAY_KEYWORDS).setMagenta(MAGENTA_KEYWORDS).setOrange(ORANGE_KEYWORDS).setPink(PINK_KEYWORDS).setRed(RED_KEYWORDS).setWhite(WHITE_KEYWORDS).setYellow(YELLOW_KEYWORDS);
	}
}