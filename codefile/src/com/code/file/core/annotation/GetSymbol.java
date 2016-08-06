package com.code.file.core.annotation;

import com.code.file.core.FilterImpl;
import com.code.file.util.Log;

/**
 * 获取元数据
 * @author Administrator
 *
 */
public class GetSymbol {

	public static String getSymbol(Object obj){
		String a = SymbolAnnotation.getSymboAnnotation(obj.getClass());
		Log.log("a:" + a);
		return a;
	}
	
	public static void main(String[] args) {
		getSymbol(new FilterImpl());
	}
}
