package com.start;

import java.util.Properties;

import com.code.file.core.model.Model;
import com.code.file.core.param.Path;
import com.code.file.util.PropertyUtil;
import com.code.file.util.file.FileUtil;
import com.code.word.WordController;
import com.code.word.WordModule;

/**
 * 生成目标文件时使用这个应用
 * @author Administrator
 *
 */
public class AppWord {
 
	private WordController control = new WordController();
	 
	private void start(String commandUrl){
		String modulePath = Path.RESOURCE_WORD + commandUrl + "/";
		Properties p = PropertyUtil.load(modulePath + "@.properties");
		Model model = new WordModule(modulePath, p);
		control.setModel(model);
		control.read();
	}
	
	public void execute(){
		String[] list = FileUtil.readDir(Path.RESOURCE_WORD);
		String file = null;
		for (int i = 0; i < list.length; i++) {
			file = list[i];
			System.out.println("file:" + file);
			start(file);
		}
	}
 
	public static void main(String[] args) {
		AppWord app = new AppWord();
		app.execute();
	}
	
}
