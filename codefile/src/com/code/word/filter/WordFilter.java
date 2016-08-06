package com.code.word.filter;

import com.code.file.core.FilterImpl;
import com.code.file.core.interfaces.Filterable;
import com.code.file.core.model.Model;
import com.code.file.core.param.Smbol;
import com.code.word.WordModule;

public class WordFilter extends FilterImpl implements Filterable{
 
	/**
	 * 定义过滤器
	 */
	@Override
	public String filter(String word, Object obj, String field) { 
		Model model = (Model) obj;
		String[] words = word.split("-"); 
		String content = ((WordModule) model).getFormat();
		if(word.trim().length() > 0){
			content = toFormatEnglish(words[0], content);
			content = toFormatChina(words[1], content); 
			content = toT(content);
			content = toField(field, content) + Smbol.tr;
		}

		return content;
	}
 
	/**
	 * 替换模块数据
	 * @param word
	 * @param model
	 * @return
	 */
	private String toField(String word, String content){
		return content = content.replaceAll("@field", word); 
	}
	
	/**
	 * t
	 * @param word
	 * @param model
	 * @return
	 */
	private String toT(String content){
		return content = content.replaceAll("@t", "\t"); 
	}
	
	/**
	 * 分离元数据
	 * @param word
	 * @param model
	 * @return
	 */
	public String toFormats(String word, Model model){
		return ((WordModule) model).getFormat().replaceAll("@", word) + "\r\n"; 
	}
	
	/**
	 * 分离元数据
	 * @param word
	 * @param model
	 * @return
	 */
	private String toFormatEnglish(String word, String content){
		return content = content.replaceAll("@e", word); 
	}
	
	/**
	 * 分离元数据
	 * @param word
	 * @param model
	 * @return
	 */
	private String toFormatChina(String word, String content){
		return content = content.replaceAll("@c", word); 
	}
}
