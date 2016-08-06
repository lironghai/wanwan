package com.code.file.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

public class DigestsUtil {
	
	/**
	 * 转换成二进制
	 * @param content
	 * @param charset
	 * @return
	 */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
	
	/**
	 * 加密
	 * @param text
	 * @param input_charset
	 * @return
	 */
	public static String md5(String text, String input_charset) {
		String md5 = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		Log.log("sign", md5);
		return md5;
	}
    
	/**
	 * 校验
	 * @param text
	 * @param sign
	 * @param input_charset
	 * @return
	 */
	public static boolean verify(String text, String sign, String input_charset) {
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	Log.log("sign", mysign);
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
	
	public static void main(String[] args) {
		boolean rs = verify("0000", "7927d0cd79707f9a9432b0ed4d07c489", "utf-8");
		Log.log("rs:" + rs);
	}
}
