package com.code.file.util.file;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.code.file.util.PropertyUtil;

/**
 * 与正则表达式相关的工具类
 * @author coco
 *
 */
public class LexUtil {
 
	public final static String remove_strings = "schema = \"EMS\",";
	public final static String replace_express = "By*Id";
	 
	public static Map<String, String> scanContent(String express, String lex){
		Map<String, String> keyMap = new HashMap<String, String>();
		String[] lexArr = lex.split(" |\n|\\(|\\)|;|.");
		for (int i = 0; i < lexArr.length; i++) {
			keyMap.put(lexArr[i], lexArr[i]);
		}
		System.out.println("MATCHS:" + keyMap);
		Iterator<String> keys = keyMap.keySet().iterator();
		//通过对map进行循环，把需要匹配的键值value：未处理的字段，转换成目标字段
		while(keys.hasNext()){
			String key = keys.next();
			String value = keyMap.get(key);
			value = getMiddle(express, value);
			if(!key.equals(value)){
				value = PropertyUtil.lowerName(value);
				keyMap.put(key, value);//当键值对不一致时修改键值对，否则删除
			}else{
				keys.remove();
				keyMap.remove(key);
			}
		}
	 
		System.out.println("MATCHS:" + keyMap);
		return keyMap;
	}
	
	public static String replaceContent(String express, String lex){
		Map<String, String> map = scanContent(express, lex);
		Iterator<String> keys = map.keySet().iterator();
		String subLex = lex;
		while(keys.hasNext()){
			String key = keys.next();
			if(map.get(key).length() > 0){
				subLex = subLex.replaceAll(key, map.get(key));
			} 
		}
		System.out.println("content:" + subLex);
		return subLex;
	}
	
	/**
	 * 将生成的代码中的某些细节删除
	 * @param express
	 * @param lex
	 * @return
	 */ 
	public static String removeField(String lex){
		return lex = lex.replaceAll("schema = \"EMS\"", lex);
	}
	
	public static boolean isGetterSetter(String lex){
		if(lex.indexOf("get") == -1 && lex.indexOf("set") == -1)
			return false;
		return true;
	}
	
	/**
	 * 替换神器，输入一个模式，输出待匹配的字符串
	 * 
	 */
	public static String getMiddle(String express, String lex){
		String[] exps = express.split("\\*");
		int start = lex.indexOf(exps[0]);
		
		if(start > -1){
			int end = lex.indexOf(exps[1]);
			if(end == -1){
				end = start + exps[0].length();
			}
			System.out.println("start:" + start);
			System.out.println("end:" + end);
			String lexHead = "";
			if(isGetterSetter(lex)){
				lexHead = lex.substring(0, start);
			} 
			lex = lexHead + lex.substring(start + exps[0].length(), end);
		}
		System.out.println("sub:" + lex);
		//如果没有匹配到则返回一个完整的lex给调用者
		return lex;
	}
	
	/**
	 * 发现非空字符的索引方法 
	 * @param lex
	 * @return
	 */
	public static int indexSmbol(String lex){
		String startStr = StringUtils.deleteWhitespace(lex);
		System.out.println("startStr after delete:" + startStr.length());
		int index = -1;
		if(startStr.length() > 0){
			index = lex.indexOf(startStr.charAt(0));
			System.out.println("ss:" + index);
		} 
		return index;
	}
	
	/**
	 * 删除左边的无用字符，从空格开始
	 * 
	 */
	public static String removeLeft(String lex){
		int start = lex.indexOf(" ");
		int end = 0;
		String left = "";
		String sub = lex;
		System.out.println("start:" + start);
		if(start != 0){ 
			left = lex.substring(0, start);
			lex = lex.substring(start);
			start = 0;
		} 
		System.out.println("lex:" + lex);
		end = indexSmbol(lex); 
		
		if(end > -1){
			sub = left + lex.substring(start, end);
			System.out.println("zz:" + sub);	
		} 
		return sub;
	}
	
	public static String replace(String express, String lex){
		String[] exps = express.split("\\*");
		String left = exps[0];
		String right = "";
		if(exps.length > 1){
			right = exps[1];
		} 
		String[] lexArr = lex.split(left);
		String content = "";
		String subLex = "";
		for (int i = 0; i < lexArr.length; i++) {
			subLex = lexArr[i];
			System.out.println("subLex:" + subLex);
			subLex = removeLeft(subLex);
			content += subLex;
		} 
		//替换掉所有Id
		content = content.replaceAll(right, "");
		System.out.println("content:" + content);
		return content;
	}
	
	public static void main(String[] args) {
		//getMiddle("By*Id", "deptByvvvvvId  deptByvvvvvId");
		//removeLeft("ZeroId   ");
		String testLex = "coco \r\n coco  \r\n  getEmsByWaterId  uuBy7788Ids";
		//scanContent("By*Id", testLex);
		replaceContent("By*Id", testLex);
		//boolean isGetterSetter = isGetterSetter("gseetEmsByWaterId");
		//System.out.println("getSet:" + isGetterSetter);
	}
}
