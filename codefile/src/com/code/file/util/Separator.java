package com.code.file.util;

import java.io.File;

public class Separator {

	public static String lineSeparator = System.getProperty("line.separator", "\n");
	
	public static String fileSeparator = File.separator;
	
	public static String pathSeparator = File.pathSeparator;

	public static void main(String[] args) {
		Log.log("line.Separator" + lineSeparator);
		Log.log("file.spearator:" + fileSeparator);
		Log.log("path.spearator:" + pathSeparator);
	}
}
