package com.code.file.core.annotation;

public class SymbolAnnotation {
	
	/**
	 * 获取Symbol注解
	 * @param classz
	 * @return
	 */
	public static String getSymboAnnotation(Class<?> classz) {
		Symbol annotation = (Symbol) classz.getAnnotation(Symbol.class);
		if (annotation != null) {
			return annotation.value();
		} else {
			return null;
		}
	}
}
