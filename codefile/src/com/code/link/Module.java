package com.code.link;

import java.util.Properties;

import com.code.file.core.model.FileVo;
import com.code.file.util.file.FileUtil;

/**
 * model 
 * @author lironghai
 *
 */
public class Module {

	private String packages;
	private String authors;
	private String module;
	private String moduleFolder;
 
	private FileVo[] fileModels;
	 
	public String getModuleFolder() {
		return moduleFolder;
	}

	public void setModuleFolder(String moduleFolder) {
		this.moduleFolder = moduleFolder;
	}

	private String outputPath;
	private String templateTypes;
	private String fileTemplate;
 
	private Properties property;
	
	public Module(String modulePath, Properties property) {
		this.fileTemplate = modulePath;
		this.property = property;//PropertyUtil.load(fileTemplate + "@.properties");
		
		module = property.getProperty("module");
		moduleFolder = property.getProperty("moduleFolder");
		 
		outputPath = property.getProperty("filePath");
		authors =  property.getProperty("authors");
		packages = property.getProperty("packages");
		templateTypes = getTemplateFiles(modulePath);
	}
	
	public void initFileModel(int length){
		FileVo model;
		this.fileModels = new FileVo[length];
		for (int i = 0; i < length; i++) {
			model = new FileVo();  
			this.fileModels[i] = model;
		}
	}
	
	/**
	 * get the file template for per folder.
	 * @param fileTemplate
	 */
	private String getTemplateFiles(String fileTemplate){
		String[] list = FileUtil.readDir(fileTemplate);
		String templateFiles = "";
		//remove the u.properties
		for (int i = 1; i < list.length; i++) {
			templateFiles += (list[i] + "/");
		}
		return templateFiles;
	}
	
	public String getModule() {
		return module;
	}
	public void setModule(String fileName) {
		this.module = fileName;
	}
	public String getOutPutPath() {
		return outputPath;
	}
	public void setOutPutPath(String filePath) { 
		this.outputPath = filePath;
	}
	public String getFileTemplate() {
		return fileTemplate;
	}
	public void setFileTemplate(String fileTemplate) {
		this.fileTemplate = fileTemplate;
	}
 
	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	/**
	 * 用于生成可替换的字段:直接读取配置文件
	 * PropertyUtil.loadProperty(fileTemplate + "@.properties");
	 * @return
	 */
	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
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
