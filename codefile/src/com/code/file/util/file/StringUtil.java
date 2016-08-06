package com.code.file.util.file;

import com.code.file.model.Integar;

public class StringUtil {
	
	/**
	 * 可以在调用方法之前指定默认替换参数
	 */
	public static String replace_defaultParam = ""; 

	/**
	 * 用一组参数来替换指定字符串中的key
	 * @param word
	 * @param key
	 * @param params
	 */
	public static String replace(String word, String key, String[] params, String defaultParam){
		int i = 0;
		String value = defaultParam; 
		while(word.indexOf(key) >= 0){
			if(i < params.length){
				value = params[i++];
			}else{
				value = defaultParam;
			}	
			word = word.replaceFirst(key, value);
		}
		return word;
	}
	
	public static String replace(String word, String key, String[] params){
		return replace(word,  key,  params, replace_defaultParam);
	}
	
	public static String replace(String word, String key, Integar sequence){
		while(word.indexOf(key) >= 0){
			word = word.replaceFirst(key, "" + sequence.i);
			sequence.i++;
		}
		return word;
	}
	
	/**
	 * 统计指定字符串出现的次数
	 * @param word
	 * @param key
	 */
	public static void count(String word, String key){
		
	}
	
	public static void main(String[] args) {
		String word = "abcvvvvabcvvvvabcccvvvv"; 
		Integar sequence = new Integar(66); 
		String target = StringUtil.replace(word, "abc", sequence);
		System.out.println("r:" + target);
		System.out.println("sequence:" + sequence.i);
	}
}
