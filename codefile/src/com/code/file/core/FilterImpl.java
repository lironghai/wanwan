package com.code.file.core;

import java.util.Date;
import java.util.Properties;

import com.code.file.core.annotation.GetSymbol;
import com.code.file.core.annotation.Symbol;
import com.code.file.core.param.FilterConfig;
import com.code.file.model.Integar;
import com.code.file.util.PropertyUtil;
import com.code.file.util.file.StringUtil;
 
@Symbol("@")
public class FilterImpl {
	
	/**
	 * 组装元数据首字母小写
	 * @param param
	 * @return
	 */
	protected String toLittle(String param){
		return GetSymbol.getSymbol(this) + PropertyUtil.lowerName(param);
	}
	
	/**
	 * 组装元数据首字母约定
	 * @param param
	 * @return
	 */
	protected String to(String param){
		return GetSymbol.getSymbol(this) + param;
	}
	
	protected String to(){
		return GetSymbol.getSymbol(this);
	}
	
	/**
	 * 直接替换掉节点
	 * @param word
	 * @param module
	 * @return
	 */
	public String toNode(String word, String node, int i){
		node = PropertyUtil.upperName(node);
		return word.replaceAll(to(FilterConfig.Node) + i, node);
	}
	 
	/**
	 * 首字母小写
	 * @param word
	 * @param module
	 * @return
	 */
	public String toLittleNode(String word, String node, int i){
		node = PropertyUtil.lowerName(node);
		return word.replaceAll(toLittle(FilterConfig.Node) + i, node);
	}

	/**
	 * 直接调用不区分大小写
	 * @param word
	 * @param module
	 * @return
	 */
	public String toNodez(String word, String module, int i){
		word = toNode(word, module, i);
		return toLittleNode(word, module, i);  
	}
	
	public String toNodeAll(String word, String[] module){
		for (int i = 0; i < module.length; i++) {
			word = toNodez(word, module[i], i + 1);
		}
		return word;
	}
	
	/**
	 * 将module字段替换掉:特殊字段替换的方法
	 * @param word
	 * @param module
	 * @return
	 */
	public String toModule(String word, String module){
		module = PropertyUtil.upperName(module);
		return word.replaceAll(to(FilterConfig.Module), module);
	}
	
	/**
	 * 首字母小写
	 * @param word
	 * @param module
	 * @return
	 */
	public String toLittleModule(String word, String module){
		module = PropertyUtil.lowerName(module);
		return word.replaceAll(toLittle(FilterConfig.Module), module);
	}

	/**
	 * 直接调用不区分大小写
	 * @param word
	 * @param module
	 * @return
	 */
	public String toModulez(String word, String module){
		word = toModule(word, module);
		return toLittleModule(word, module);  
	}
	
	/**
	 * 替换文件内的特点childModule, 就是作用范围只有一个文件的module.满足连续性文件的要求。
	 * @Child 首字母大写
	 * @param word
	 * @param child
	 * @return
	 */
	public String toChild(String word, String child){
		child = PropertyUtil.upperName(child);
		return word.replaceAll(to(FilterConfig.Child), child);
	}
	
	/**
	 * 首字母小写。
	 * @child 首字母小写
	 * @param word
	 * @param child
	 * @return
	 */
	public String toLittleChild(String word, String child){
		child = PropertyUtil.lowerName(child);
		return word.replaceAll(toLittle(FilterConfig.Child), child); 
	}
	
	/**
	 * 描述
	 * @child 首字母小写
	 * @param word
	 * @param child
	 * @return
	 */
	public String toDescribe(String word, String describe){
		return word.replaceAll(to(FilterConfig.describe), describe); 
	}
	
	/**
	 * 生成系统时间
	 * @param word
	 * @return
	 */
	public String toTime(String word){
		return word.replaceAll(to(FilterConfig.time), (new Date()).toString());
	}
	
	/**
	 * 生成全局的Index
	 * @param word
	 * @return
	 */
	public String toIndex(String word, Integar integer){
		return word = StringUtil.replace(word, to(FilterConfig.index), integer);
	}
	
	/**
	 * 通过键值对指定的属性
	 * @param word
	 * @param e
	 * @param key
	 * @return
	 */
	public String toProperty(String word, Properties e, String key){
		String value = e.getProperty(key);
		return word.replaceAll(to() + key, value);
	}
}
