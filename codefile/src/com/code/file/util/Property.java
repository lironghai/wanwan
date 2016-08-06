package com.code.file.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

	/**
	 * 初始化时给对象
	 */
	private static Properties property;

	public String get(String key) {
		String value = null;
		if (property.containsKey(key)) {
			value = property.getProperty(key);
		}
		return value;
	}

	public static Properties getProperty() {
		return property;
	}

	public static void setProperty(Properties property) {
		Property.property = property;
	}
	
	/**
	 * 加载属性文件 file:src/config/@.properties
	 * @param file
	 */
	public static void load(String file){
		property = new Properties();
		InputStream in = Property.class.getResourceAsStream(file);
		try {
			property.load(in); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载属性文件
	 * @param file ./resource/template/test.properties
	 * @return
	 */
	public static Properties load2(String file){
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
	
	public static void main(String[] args) {
		load("/config/@.properties");
		Log.log(property);
	}
}
