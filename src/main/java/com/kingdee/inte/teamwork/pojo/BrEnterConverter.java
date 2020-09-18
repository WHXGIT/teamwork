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
 * @date 2020/7/1 19:05
 */
public class BrEnterConverter implements Converter<String> {
	@Override
	public Class supportJavaTypeKey() {
		return String.class;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return CellDataTypeEnum.STRING;
	}

	@Override
	public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		String cellVal = cellData.getStringValue();
		cellVal = cellVal.replaceAll("\r\n", "<br/>");
		cellVal = cellVal.replaceAll("\n", "<br/>");
		cellVal = cellVal.replaceAll("\r", "<br/>");
		return cellVal;
	}

	@Override
	public CellData convertToExcelData(String s, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		s = s.replaceAll("<br/>", "\r\n");
		CellData cell = new CellData(s);
		return cell;
	}
}
