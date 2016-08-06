package com.code.file.util.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.code.file.util.Log;

/**
 * 同步AD时所有与List有关的操作统一放置的工具类
 * 
 * @author
 *
 */
public class ListUtil {

	/**
	 * 判断一个字符串是否是数字
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isNumber(String id) {
		boolean isNum = id.matches("\\d+");
		return isNum;
	}
 

 
	/**
	 * 截断数组，取出其中有值的一部分
	 * 
	 * @param objs
	 * @param length
	 * @return
	 */
	public static String[] cutArry(String[] source, int length) {
		String[] result = new String[length];
		System.arraycopy(source, 0, result, 0, length);
		return result;
	}

	/**
	 * 搜索list是否有对象：简单版
	 * 
	 * @param list
	 * @param code
	 * @return
	 */
	public static int find(List<Object[]> list, Object code) {
		for (Object[] obs : list) {
			if (obs != null && obs[0].equals(code)) {
				return Integer.parseInt(obs[1].toString());
			}
		}
		return -1;
	}

	/**
	 * 搜索list是否有对象:复杂版
	 * 
	 * @param list
	 * @param id
	 * @param equalIndex 用于查询object数组第几个元素
	 * @param returnIndex 返回object数组第几个元素
	 * @return
	 */
	public static int find(List<Object[]> list, Object id, int equalIndex, int returnIndex) {
		for (Object[] obs : list) {
			if (obs != null && obs[equalIndex].equals(id)) {
				return Integer.parseInt(obs[returnIndex].toString());
			}
		}
		return -1;
	}
	
	/**
	 * 搜索list是否有对象
	 * 
	 * @param list
	 * @param code
	 * @return
	 */
	public static String find(List<String> list, String code) {
		for (String str : list) {
			if (str != null && str.equals(code)) {
				return code;
			}
		}
		return null;
	}
	
	/**
	 * 过滤掉null对象
	 * 
	 * @param list
	 * @return
	 */
	public static List<Listable> filterNull(List<Listable> list){
		List<Listable> result = new ArrayList<Listable>();
		for(Listable object: list){
			if(object != null){
				result.add(object);
			}
		}
		return result;
	}
	
	public static void filter(List<?> list, Object obj){
		list.removeAll(Collections.singleton(obj));
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("cc");
		list.add("cce");
		list.add(null);
		Log.log(list.toString());
		filter(list, null);
		Log.log(list.toString());
	}
 
}
