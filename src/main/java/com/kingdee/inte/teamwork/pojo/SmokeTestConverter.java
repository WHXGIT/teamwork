package com.kingdee.inte.teamwork.pojo;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * description:
 *
 * @author Administrator
 * @date 2020/6/30 10:14
 */
public class SmokeTestConverter implements Converter<Integer> {
	@Override
	public Class supportJavaTypeKey() {
		return Integer.class;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return CellDataTypeEnum.STRING;
	}

	@Override
	public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		return "是".equals(cellData.getStringValue()) ? 1 : 0;
	}

	@Override
	public CellData convertToExcelData(Integer value, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		return new CellData(value.equals(1) ? "是" : "否");
	}
}
