package com.code.file.util;

/**
 * 数学函数
 * @author coco
 *
 */
public class MathUtil {

	/**
	 * 返回一个随机整数
	 * @param max
	 * @return
	 */
	public static int random(int max){
		return (int)(Math.random() * (double)max);
	}
	
	public static int index(){
		return 0;
	}
	
	public static double move(double num, int bit){
		num = bit > 0 ? 
		num * (bit * 10.0):
		num /(bit * 10.0);
		return num;
	}
	
	/**
	 * 以二进制的形式往左移动
	 * @param num
	 * @param bit
	 * @return
	 */
	public static int move(int num, int bit){
		num = num >> bit;
		return num;
	}
	
	public static void main(String[] args) {
		System.out.println("num:" + move(0x14, 2));
	}
}
