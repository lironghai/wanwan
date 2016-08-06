package com.code.file.express;

import java.io.File;

import com.code.file.core.param.Path;
import com.code.file.util.file.FileUtil;

/**
 * Express是将上级文件夹的配置分发到子级文件里面的一个分发器
 * 	——Express是针对@.properties文件进行修改的一种工具.
 *  ——当一个文件夹里没有@.properties文件时，可以用这个Express去进行修改.
 * 
 * @author Administrator
 *
 */
public class ExpressController {

	public static final String express_file = "Express.properties";
	
	public static final String property_file = "@.properties";
	
	private Express express = new Express();
	
	public void init(){
		readFile(Path.RESOURCE_LIST + express_file);
		readDir(Path.RESOURCE_LIST); 
		write();
	}
	
	public void readFile(String path){
		express.content =  FileUtil.readFile(path);
	}
	
	public void readDir(String path){
		express.dir = FileUtil.readFileList(path, true);
	}
	
	public void writeFile(String dir){
		FileUtil.createFileUion(property_file, dir, express.content); 
	}
	
	public void write(){
		File[] list = express.dir;
		String dir = null;
		for (File file :list) {
			dir = file.getPath();
			System.out.println("dir:" + dir);
			writeFile(dir);
		} 
	}
	
	public static void main(String[] args) {
		ExpressController controller = new ExpressController();
		controller.init();
		System.out.println(controller.express); 
		
	}
}
