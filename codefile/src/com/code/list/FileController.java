package com.code.list;

import com.code.file.core.Controller;
import com.code.file.core.interfaces.Control;
import com.code.file.core.model.FileVo;
import com.code.file.core.model.Model;
import com.code.file.util.TemplateTypeUtils;
import com.code.file.util.file.FileUtil;
import com.code.list.filter.ListFilter;

/**
 * 主要控制类
 * 
 * @author lironghai
 * 
 */
public class FileController extends Controller implements Control{
 
	public FileController(){
		this.writeService = new FileWriteService();
	}
	  
	/**
	 * ./src/resource/template/test.template
	 * 
	 * @param model
	 */
	public void read() {
		String[] tempFiles = TemplateTypeUtils.toFiles(((FileModule) model).getTemplateTypes());
		String type;
		for (int i = 0; i < tempFiles.length; i++) {
			type = tempFiles[i];
			//type = PropertyUtil.upperName(type);
			String[] modules = TemplateTypeUtils.toFiles(model.getModule());
			String file = null;
			for (int j = 0; j < modules.length; j++) {
				file = toFile(type, modules[j], model);
			}
			this.readedFiles.add(file);
			this.writeFiles.add(this.writeService.getFile());
		} 
	}

	public String toFile(String templateType, String module, Model models){
		String file = model.getFileTemplate() + templateType;   
		String content = FileUtil.readFile(file);//获取文件与字节流中的内容
		toFilter(content, module, ((FileModule) models).getFileModels());//过滤文件 
		writeService.write(model, module, TemplateTypeUtils.classAppend(templateType));
		return file;
	}
	 
	private void toFilter(String content, String module, FileVo[] file){
		FileVo model;
		ListFilter vo;
		ListFilter.readStatic(content, this.getModel(), null);//读取全局元数据
		for (int i = 0; i < file.length; i++) {
			model = file[i];
			if(model != null){
				vo = new ListFilter(module, model.getName(), model.getDescribe(), model.getNode());
				model.setContent(vo.filter(content, this.getModel(), null));
				System.out.println(file[i].getContent());
			} 
		} 	
	}
 
}
