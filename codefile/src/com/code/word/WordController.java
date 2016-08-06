package com.code.word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.code.file.core.Controller;
import com.code.file.core.interfaces.Control;
import com.code.file.core.model.Model;
import com.code.file.util.PropertyUtil;
import com.code.file.util.TemplateTypeUtils;

/**
 * 主要控制类:读取模板的内容,放到model里面
 * 
 * @author lironghai
 * 
 */
public class WordController extends Controller implements Control{
 
	private Scanner in;
	private WordWriteService view = new WordWriteService();
   
	/**
	 * ./src/resource/template/test.template
	 * 
	 * @param model
	 */
	public void read() {
		WordModule module = (WordModule)model;
		String[] tempFiles = TemplateTypeUtils.toFiles(module.getTemplateTypes());
		String[] modules = TemplateTypeUtils.toFiles(model.getModule());
		String type;
		for (int j = 0; j < modules.length; j++) {
			for (int i = 0; i < tempFiles.length; i++) {
				type = tempFiles[i];
				type = PropertyUtil.upperName(type);
				toFile(type, modules[j], null); 
			} 
			view.write(model, modules[j], TemplateTypeUtils.classAppend("line.word"));
		}
	}

	/**
	 * 读取文件夹中的内容
	 * @param templateType
	 * @param module
	 * @param models
	 * @return
	 */
	public String toFile(String templateType, String module, Model vo){
		String file = null;
		try {
			file = model.getFileTemplate() + templateType;
			in = new Scanner(new File(file));
			toText(module);
			in.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * 生成每个FileModel并每个文件的内容
	 * 
	 */
	public void toText(String module){
		String content = "";
		while (in.hasNextLine()) {
			content = (content + in.nextLine() + "\r\n");
		}
		((WordModule)this.model).setContent(content);	
	}
	  
 	public WordWriteService getView() {
		return view;
	}
  
}
