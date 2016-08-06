package com.start;

import java.util.Properties;

import com.code.file.core.model.Model;
import com.code.file.core.param.Path;
import com.code.file.util.PropertyUtil;
import com.code.file.util.file.FileUtil;
import com.code.folder.FolderController;
import com.code.list.FileModule;

/**
 * 操作文件夹的功能
 * @author Administrator
 *
 */
public class AppFolder {
 
	/**
	 * single 
	 */
	private FolderController control = new FolderController();
 
	private void start(String commandUrl){
		String modulePath = Path.RESOURCE_FOLDER + commandUrl + "/";
		Properties p = PropertyUtil.load(modulePath + "@.properties");
		Model model = new FileModule(modulePath, p);
		control.setModel(model);
		control.read();
	}
	
	/**
	 * create a dir file
	 */
	public void execute(){
		String[] list = FileUtil.readDir(Path.RESOURCE_FOLDER, true);
		String file = null;
		for (int i = 0; i < list.length; i++) {
			file = list[i];
			System.out.println("file:" + file);
			start(file);
		}
	}
	 
	public static void main(String[] args) {
		AppFolder app = new AppFolder();
		app.execute();
	}
	
}
