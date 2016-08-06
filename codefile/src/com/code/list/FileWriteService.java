package com.code.list;

import com.code.file.core.FilterImpl;
import com.code.file.core.WriteService;
import com.code.file.core.model.Model;
import com.code.file.core.param.Path;
import com.code.file.util.file.FileUtil;

/**
 * view
 * @author coco
 *
 */
public class FileWriteService extends WriteService{
	
	private FilterImpl filter = new FilterImpl();
	
	/**
	 * write 
	 */
	public void write(Model model, String module, String append) {
		//String[] files = TemplateTypeUtils.toFiles(model.getModule());
		//System.out.println("files:" + files.toString());
		//toFile(model, module, append);
		
		//如果存在连续性文件属性就生成连续性文件
		toFileList(model, module, append);
	}
	
	/**
	 * 生成多个文件的方法：文件参数通过moduleFiles指定
	 * @param model
	 * @param module
	 * @param append
	 */
	private void toFileList(Model model, String module, String append){
		String dir = Path.ROOT + model.getOutputPath() + "/" + module + "/" + model.getModuleFolder();
		String moduleFile;
		String content;
		for (int i = 0; i < ((FileModule) model).getFileModels().length; i++) {
			moduleFile = ((FileModule) model).getFileModels()[i].getName();
			content = ((FileModule) model).getFileModels()[i].getContent();		 
			this.file = dir + "/" + filter.toModulez(append, moduleFile);
			FileUtil.createFile(this.file, dir, content);
		}
	}
  
}
