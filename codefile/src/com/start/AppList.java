package com.start;

import java.util.Properties;

import com.code.file.core.interfaces.Control;
import com.code.file.core.model.Model;
import com.code.file.core.param.Path;
import com.code.file.util.PropertyUtil;
import com.code.file.util.file.FileUtil;
import com.code.list.FileController;
import com.code.list.FileModule;

/**
 * main File
 * @author coco
 *
 */
public class AppList {
 
	/**
	 * single 
	 */
	private Control control = new FileController();
 
	private void start(String commandUrl){
		String modulePath = Path.RESOURCE_LIST + commandUrl + "/";
		Properties p = PropertyUtil.load(modulePath + "@.properties");
		Model model = new FileModule(modulePath, p);
		control.setModel(model);
		control.read();
	}
	
	/**
	 * create a dir file
	 */
	public void execute(){
		String[] list = FileUtil.readDir(Path.RESOURCE_LIST, true);
		String file = null;
		for (int i = 0; i < list.length; i++) {
			file = list[i];
			System.out.println("file:" + file);
			start(file);
		}
	}
	 
	public static void main(String[] args) {
		AppList app = new AppList();
		app.execute();
	}
	
}
