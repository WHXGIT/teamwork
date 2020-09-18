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
 * @date 2020/6/30 10:15
 */
public class StatusConverter implements Converter<Integer> {
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
		String val = cellData.getStringValue();
		int result;
		switch (val) {
			case "草稿":
				result = 0;
				break;
			case "完成":
				result = 1;
				break;
			case "废弃":
				result = 2;
				break;
			case "删除":
				result = 3;
				break;
			default:
				result = 0;
				break;
		}
		return result;
	}

	@Override
	public CellData convertToExcelData(Integer val, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		String result;
		switch (val) {
			case 0:
				result = "草稿";
				break;
			case 1:
				result = "完成";
				break;
			case 2:
				result = "废弃";
				break;
			case 3:
				result = "删除";
				break;
			default:
				result = "草稿";
				break;
		}
		return new CellData(result);
	}
}
