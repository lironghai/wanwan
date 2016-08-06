package com.code.file.util;

public class TemplateTypeUtils {

	public final static String ENTITY = "Entity";
 
	/**
	 * 模板文件后缀替换方法
	 * @param templateType
	 * @return
	 */
	public static String classAppend(String templateType){
		if(templateType.equals(ENTITY))
			return "";
		else
			return templateType;
	}
	
	public static String[] toFiles(String templateTypes){
		return templateTypes.split("/");
	}
}
