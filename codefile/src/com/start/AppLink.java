package com.start;

import java.util.Properties;

import com.code.file.core.param.Path;
import com.code.file.util.Property;
import com.code.file.util.PropertyUtil;
import com.code.file.util.file.FileUtil;
import com.code.link.FileLinkController;
import com.code.link.Module;

/**
 * 生成目标文件时使用这个应用
 * @author Administrator
 *
 */
public class AppLink {
 
	private FileLinkController control = new FileLinkController();
	
	public AppLink(boolean isTemplate){
	 
	}
	
	private void start(String commandUrl){
		String modulePath = Path.RESOURCE_LINK + commandUrl + "/";
		Properties p = PropertyUtil.load(modulePath + "@.properties");
		Property.setProperty(p); 
		Module model = new Module(modulePath, p);
		control.setModel(model);
		control.read();
	}
	
	public void execute(){
		String[] list = FileUtil.readDir(Path.RESOURCE_LINK);
		String file = null;
		for (int i = 0; i < list.length; i++) {
			file = list[i];
			System.out.println("file:" + file);
			start(file);
		}
	}
 
	public static void main(String[] args) {
		AppLink app = new AppLink(false);
		app.execute();
	}
	
}
