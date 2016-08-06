package com.code.file.util.file.excel;

import java.util.Map;

/**
 * 检查excel中每一行的数据是否合法
 */
public interface CheckExcel {
	/**
	 * 返回true合法
	 *
	 * @param data
	 *            excel中每一行的数据
	 * @return
	 */
	public boolean check(Map<String, String> data);
}