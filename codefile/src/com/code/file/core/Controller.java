package com.code.file.core;

import java.util.LinkedList;
import java.util.List;

import com.code.file.core.model.Model;

public abstract class Controller {
	
	/**
	 * 已经读取的文件list
	 */
	protected List<String> readedFiles = new LinkedList<String>();
	
	/**
	 * 输出文件list
	 */
	protected List<String> writeFiles = new LinkedList<String>();
	
	/**
	 * 初始化放到子类
	 */
	protected WriteService writeService; 
	
	protected Model model;
	 
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public WriteService getView() {
		return writeService;
	}

	public List<String> getFiles() {
		return readedFiles;
	}

	public List<String> getViewFiles() {
		return writeFiles;
	}

}
