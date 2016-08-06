package com.code.file.core;

import com.code.file.core.model.Model;

public abstract class WriteService {
	
	protected String file;
	
	public void write(Model model, String module, String append){
		
	}
	
	public String getFile(){
		return file;
	}
}
