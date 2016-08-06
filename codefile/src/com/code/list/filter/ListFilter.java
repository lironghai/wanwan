package com.code.list.filter;

import java.util.Enumeration;
import java.util.Properties;

import com.code.file.core.FilterImpl;
import com.code.file.core.annotation.Symbol;
import com.code.file.core.interfaces.Filterable;
import com.code.file.core.model.Model;
import com.code.file.model.Integar;
import com.code.file.util.file.LexUtil;

@Symbol
public class ListFilter extends FilterImpl implements Filterable{
	public static int static_index;//全局的index只初始化一次
	private String module;
	private String child;
	private String describe;
	
	private String[] node;
	
	public ListFilter(String module, String child, String desc, String[] node){
		this.module = module;
		this.child = child;
		this.describe = desc;
		this.node = node;
	}
	
	/**
	 * 读取全局元数据:只读一次,并在模块作用域之前读
	 * @param word
	 * @param obj:vo
	 * @param type
	 */
	public static void readStatic(String word, Object obj, String type){
		Model model = (Model) obj;
		Properties e = model.getProperty();
		if(e.containsKey("staticIndex")){
			static_index = Integer.parseInt(e.getProperty("staticIndex"));
		}	
	}
	
	public String filter(String word, Object obj, String type){
		Model model = (Model) obj;
		Properties e = model.getProperty();
		Enumeration<Object> keys = e.keys(); 
		while(keys.hasMoreElements()){
			String field = (String)keys.nextElement();
			if(field.equals("module")){
				word = toModule(word, this.module);
			}else if(field.equals("moduleFiles")){
				//直接大小写一起替换
				word = toChild(word, this.child);
				word = toLittleChild(word, this.child);
				//替换中文内容
				word = toDescribe(word, this.describe);
				//替换节点内容
				if(this.node != null){
					word = toNodeAll(word, this.node);		
				} 
				//替换index内容
				if(static_index > 0){
					Integar integer = new Integar(static_index);
					word = toIndex(word, integer);
					static_index = integer.i++;
				}
			}else{
				word = toProperty(word, e, field);
				//String value = e.getProperty(field);
				//word = word.replaceAll("@" + field, value);
			}
		}
		word = toModify(word); 
		return word;
	}
	
	/**
	 * 修饰文件中某些字段
	 * @param word
	 * @return
	 */
	public String toModify(String word){
		word = toTime(word);
		word = LexUtil.replaceContent(LexUtil.replace_express, word);
		word = word.replaceAll(LexUtil.remove_strings, ""); 
		return word;
	}
 
}
