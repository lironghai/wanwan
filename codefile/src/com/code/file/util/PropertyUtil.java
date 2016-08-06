package com.code.file.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 读取属性文件的工具类
 * @author coco
 *
 */
public class PropertyUtil {
  
	/**
	 * 加载属性文件
	 * @param file ./resource/template/test.properties
	 * @return
	 */
	public static Properties load(String file){
		Properties map = new Properties(); 
		InputStream in;
		try {
			in = new FileInputStream(file);
			map.load(in);
			in.close();
		} catch (FileNotFoundException e1) { 
			e1.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		System.out.println(map);
		return map;
	}
	 
	/**
	 * 键值对转换：根据key值寻找value
	 * @param map
	 * @param key
	 * @return
	 */
	public static String get(Properties map, String key){
		return map.getProperty(key);
	}
	
	/**
	 * 寻找所有的key值
	 * @param map
	 * @return
	 */
	public static Enumeration<Object> getKeys(Properties map){
		return map.keys();
	}
	
	/**
	 * 首字母大写
	 * @param name
	 * @return
	 */
	public static String upperName(String name) {
	    StringBuilder result = new StringBuilder();
	    if (name != null && name.length() > 0) {
	        result.append(name.substring(0, 1).toUpperCase());
	        result.append(name.substring(1, name.length())); 
	    }
	    return result.toString();
	}
	 
	/**
	 * 首字母小写
	 * @param name
	 * @return
	 */
	public static String lowerName(String name) {
	    StringBuilder result = new StringBuilder();
	    if (name != null && name.length() > 0) {
	        result.append(name.substring(0, 1).toLowerCase());
	        result.append(name.substring(1, name.length()));
	    }
	    return result.toString();
	}
	 
}
