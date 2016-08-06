package com.code.file.util;

import java.util.Date;

@SuppressWarnings("deprecation")
public class Version {

	public static final Date START_DATE = new Date(2015, 06, 03);
	
	public static float getVersion(Date start, Date end){
		float day = end.getDate() - start.getDate();
		float nodeVersion = (day/180)/4;//子版本
		float version = (day/180);//主版本
		return version + nodeVersion;
	}
	
	public static void main(String[] args) {
		Date start = new Date();
		start.setYear(2015);
		start.setMonth(1);
		start.setDate(1);
		float version = Version.getVersion(start, new Date());
		System.out.println("version:" + version);
	}
}
