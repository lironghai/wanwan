package com.code.folder;

import java.util.Properties;

import com.code.file.core.interfaces.Module;
import com.code.file.core.model.FileVo;
import com.code.file.core.model.Model;
import com.code.file.util.file.FileUtil;

public class FolderModule extends Model implements Module {

	private FileVo[] fileModels; 
	private String templateTypes;
	
	public FolderModule(String fileTemplate, Properties property) {
		super(fileTemplate, property); 
		this.initFileModel(property.getProperty("moduleFiles"));
		this.templateTypes = FileUtil.getFiles(fileTemplate);
	}
	
	public void initFileModel(String moduleFiles){
		String[] fileArray = moduleFiles.split("/");
		FileVo model;
		this.fileModels = new FileVo[fileArray.length - 1];
		String nameNode = null;
		String node = null;
		String[] nameNodeArr = null;
		for (int i = 0; i < fileModels.length; i++) {
			model = new FileVo();
			//设置子模块的child属性
			nameNode = fileArray[i].split("-")[0];
			nameNodeArr = nameNode.split("\\[|\\]");
			model.setName(nameNodeArr[0]);
			if(nameNodeArr.length > 1){
				node = nameNodeArr[1];	
				model.setNode(node.split(","));
			}
			
			//设置子模块的describe属性
			model.setDescribe((fileArray[i].split("-")[1])); 
			this.fileModels[i] = model;
		}
	}
	
	public String getTemplateTypes() {
		return templateTypes;
	}

	public void setTemplateTypes(String templateTypes) {
		this.templateTypes = templateTypes;
	}

	public FileVo[] getFileModels() {
		return fileModels;
	}

	public void setFileModels(FileVo[] fileModels) {
		this.fileModels = fileModels;
	}
 	
}
