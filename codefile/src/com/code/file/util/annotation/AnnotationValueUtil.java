package com.code.file.util.annotation;

import java.lang.reflect.Field;

public class AnnotationValueUtil {
	
	public static void getAnnotationInfo(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(AnnotationValue.class)) {
				AnnotationValue value = (AnnotationValue) field.getAnnotation(AnnotationValue.class);
				System.out.println("注解：" + value.value());
			}else if(field.isAnnotationPresent(AnnotationFunction.class)){
				AnnotationFunction value = (AnnotationFunction) field.getAnnotation(AnnotationFunction.class);
				System.out.println("注解：" + value.value());
			}
		}
	}
	
	public static void print(Class<?> classz) {
		AnnotationFunction function = (AnnotationFunction) classz.getAnnotation(AnnotationFunction.class);
		if (function != null) {
			System.out.println("function:" + function.value());
		} else {
			System.out.println("function unknown!");
		}
	}
	
	public static void printClass(Class<?> classz) {
		AnnotationClass annotation = (AnnotationClass) classz.getAnnotation(AnnotationClass.class);
		if (annotation != null) {
			System.out.println("annotation:" + annotation.value());
		} else {
			System.out.println("annotation unknown!");
		}
	}
}
