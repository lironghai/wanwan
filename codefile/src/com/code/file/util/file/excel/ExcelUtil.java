package com.code.file.util.file.excel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Excel导入的工具类.
 */
public class ExcelUtil {

	// 成功
	public static final Integer STATUS_OK = Integer.valueOf(1);
	// 失败
	public static final Integer STATUS_NO = Integer.valueOf(0);

	/**
	 * 私有化构造器
	 */
	private ExcelUtil() {

	}

	/**
	 * 获取excel文件中的数据对象
	 *
	 * @param is
	 *            excel
	 * @param excelColumnNames
	 *            excel中每个字段的英文名(应该与pojo对象的字段名一致,顺序与excel一致)
	 * @return excel每行是list一条记录，map是对应的"字段名-->值"
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> getImportData(InputStream is, List<String> excelColumnNames)
			throws Exception {
 
		if (is == null) {
			return Collections.emptyList();
		}

		Workbook workbook = null;
		try {
			// 拿到excel
			workbook = Workbook.getWorkbook(is);
		} catch (BiffException e) { 
			return Collections.EMPTY_LIST;
		} catch (IOException e) { 
			return Collections.EMPTY_LIST;
		}
	 
		if (workbook == null) {
			return Collections.emptyList();
		}

		// 第一个sheet
		Sheet sheet = workbook.getSheet(0);
		// 行数
		int rowCounts = sheet.getRows() - 1; 
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(rowCounts - 1);

		// 双重for循环取出数据
		for (int i = 1; i < rowCounts; i++) {
			Map<String, String> params = new HashMap<String, String>();
			// i,j i:行 j:列
			for (int j = 0; j < excelColumnNames.size(); j++) {
				Cell cell = sheet.getCell(j, i);
				params.put(excelColumnNames.get(j), cell.getContents());
			}

			list.add(params);
		}

		return list;
	}

	/**
	 * 获取导入数据为对象的List
	 *
	 * @param data
	 * @param clazz
	 * @param excelColumnNames
	 * @param checkExcel
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> makeData(List<Map<String, String>> data, Class<T> clazz, List<String> excelColumnNames,
			CheckExcel checkExcel) throws Exception {
		if (data == null || data.isEmpty() || clazz == null || checkExcel == null) {
			return Collections.EMPTY_LIST;
		}

		List<T> result = new ArrayList<T>(data.size());
		for (Map<String, String> d : data) {
			if (checkExcel != null && !checkExcel.check(d)) {
				continue;
			}
			T entity = clazz.newInstance();
			for (String column : excelColumnNames) {
				BeanUtils.setProperty(entity, column, d.get(column));
			}

			result.add(entity);
		}

		return result;
	}
}