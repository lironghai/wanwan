package com.code.link;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.code.file.core.model.FileVo;
import com.code.file.util.PropertyUtil;
import com.code.file.util.TemplateTypeUtils;

/**
 * 主要控制类
 * 
 * @author lironghai
 * 
 */
public class FileLinkController {

	private Module model;
	private Scanner in;
	private FileLinkWriteService view = new FileLinkWriteService();
 
	public Module getModel() {
		return model;
	}

	public void setModel(Module model) {
		this.model = model;
	}

	/**
	 * ./src/resource/template/test.template
	 * 
	 * @param model
	 */
	public void read() {
		String[] tempFiles = TemplateTypeUtils.toFiles(model.getTemplateTypes());
		String[] modules = TemplateTypeUtils.toFiles(model.getModule());
		String type;
		for (int j = 0; j < modules.length; j++) {
			model.initFileModel(tempFiles.length);
			for (int i = 0; i < tempFiles.length; i++) {
				type = tempFiles[i];
				type = PropertyUtil.upperName(type);
				toFile(type, modules[j], model.getFileModels()[i]); 
			} 
			view.write(model, modules[j], TemplateTypeUtils.classAppend("cc"));
		}
	}

	/**
	 * 读取文件夹中的内容
	 * @param templateType
	 * @param module
	 * @param models
	 * @return
	 */
	private String toFile(String templateType, String module, FileVo fileVo){
		String file = null;
		try {
			file = model.getFileTemplate() + templateType;
			in = new Scanner(new File(file));
			toText(module, fileVo);
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
	public void toText(String module, FileVo fileVo){
		String content = "";
		while (in.hasNextLine()) {
			content = (content + in.nextLine() + "\r\n");
		}
		content = content.substring(0, content.length() - 2);
		fileVo.setContent(content);	
	}
	  
 	public FileLinkWriteService getView() {
		return view;
	}

	 
}
