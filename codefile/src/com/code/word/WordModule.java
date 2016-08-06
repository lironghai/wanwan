package com.code.word;

import java.util.Properties;

import com.code.file.core.model.Model;
import com.code.file.util.file.FileUtil;
import com.code.word.filter.FileListable;

/**
 * model 
 * @author lironghai
 *
 */
public class WordModule extends Model implements FileListable{
 
	protected String format;
 
	protected String content;
	 
	protected String templateTypes;
	
	protected String field;


	public WordModule(String fileTemplate, Properties property) {
		super(fileTemplate, property); 
		format = property.getProperty("format"); 
		templateTypes = getTemplateFiles(fileTemplate);
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
	 
	public String getTemplateTypes() {
		return templateTypes;
	}

	public void setTemplateTypes(String templateTypes) {
		this.templateTypes = templateTypes;
	}
 
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	 
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
