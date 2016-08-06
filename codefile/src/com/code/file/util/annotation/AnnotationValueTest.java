package com.code.file.util.annotation;

import com.code.file.util.Log;

public class AnnotationValueTest {

	@AnnotationValue("vvvv-1988")
	private String story;
	
	@AnnotationFunction("oooo-1988")
	private void setStory(String story){
		Log.log(story);
	}
	
	public void logStory(){
		Log.log(story);
	}
    
	public static void main(String[] args) {
		AnnotationValueUtil.getAnnotationInfo(AnnotationValueTest.class);
		AnnotationValueUtil.print(AnnotationValueTest.class);
		AnnotationValueUtil.printClass(AnnotationValueTest.class);
	}
}
