package com.code.file.util.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	public static void readZip(String source) throws IOException{
		FileOutputStream fout = new FileOutputStream(source);
		ZipOutputStream zout = new ZipOutputStream(fout);
		System.out.println("zout:" + zout);
		zout.close();
	}
}
