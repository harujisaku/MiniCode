package harujisaku.minicode.file;

import java.io.File;
import java.io.FilenameFilter;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import harujisaku.minicode.file.*;
import harujisaku.minicode.pane.*;

public class LoadConfigFolder implements FilenameFilter{
	File autoComplete = new File("config\\autocomplete\\");
	File highlight = new File("config\\highlight\\");
	File theme = new File("config\\theme\\");
	public LoadConfigFolder(){
		
	}
	
	public LoadConfigFolder(File autoComplete,File highlight){
		this.autoComplete=autoComplete;
		this.highlight=highlight;
	}
	
	public LoadConfigFolder(File autoComplete,File highlight,File theme){
		this.autoComplete=autoComplete;
		this.highlight=highlight;
		this.theme=theme;
	}
	
	public JRadioButtonMenuItem[] LoadAutoCompleteFolder(){
		File[] autoCompleteList = autoComplete.listFiles(new LoadConfigFolder());
		if (autoCompleteList==null) {
			return null;
		}
		List<JRadioButtonMenuItem> radioButtonMenuItem = new ArrayList<JRadioButtonMenuItem>();
		for (File file :autoCompleteList ) {
			String name=file.getName();
			JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(name);
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					if (CodePaneManager.selectingCodePanel!=null) {
						CodePaneManager.selectingCodePanel.getCodePane().autoComplete.setAutoComplete(ReadACSFile.readFile(file));
					}
				}
			});
			radioButtonMenuItem.add(menuItem);
		}
		return radioButtonMenuItem.toArray(new JRadioButtonMenuItem[radioButtonMenuItem.size()]);
	}
	
	public JRadioButtonMenuItem[] LoadHighlightFolder(){
		File[] highlightList = highlight.listFiles(new LoadConfigFolder());
		if (highlightList==null) {
			return null;
		}
		List<JRadioButtonMenuItem> radioButtonMenuItem = new ArrayList<JRadioButtonMenuItem>();
		for (File file : highlightList ) {
			String name=file.getName();
			JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(name);
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					if (CodePaneManager.selectingCodePanel!=null) {
						CodePaneManager.selectingCodePanel.getCodePane().setSyntaxHighLight(ReadSHSFile.readFile(file));
					}
				}
			});
			radioButtonMenuItem.add(menuItem);
		}
		return radioButtonMenuItem.toArray(new JRadioButtonMenuItem[radioButtonMenuItem.size()]);
	}
	
	public JRadioButtonMenuItem[] LoadThemeFolder(){
		File[] themeList = theme.listFiles(new LoadConfigFolder());
		if (themeList==null) {
			return null;
		}
		List<JRadioButtonMenuItem> radioButtonMenuItem = new ArrayList<JRadioButtonMenuItem>();
		for (File file : themeList ) {
			String name=file.getName();
			JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(name);
			menuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					ReadTHSFile.readFile(file);
				}
			});
			radioButtonMenuItem.add(menuItem);
		}
		return radioButtonMenuItem.toArray(new JRadioButtonMenuItem[radioButtonMenuItem.size()]);
	}
	
	@Override
	public boolean accept(File dir,String name) {
		boolean result=false;
		if (name.endsWith("acs")||name.endsWith("shs")||name.endsWith("ths")) {
			result=true;
		}
		return result;
	}
}