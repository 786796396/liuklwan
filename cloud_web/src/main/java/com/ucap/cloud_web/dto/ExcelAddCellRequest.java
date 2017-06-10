package com.ucap.cloud_web.dto;

import jxl.write.WritableFont;
import jxl.write.WritableSheet;

/**
 * 描述： 导出excel  addCell 填充数据封装对象
 * 包：com.ucap.cloud_web.dto
 * 文件名称：ExcelAddCellRequest
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-10-19下午5:17:20 
 * @version V1.0
 */
public class ExcelAddCellRequest {
	private WritableSheet sheet;
	private WritableFont normalFont;
	private int z;
	private int y;
	private int yNum;
	private Object value;
	public WritableSheet getSheet() {
		return sheet;
	}
	public void setSheet(WritableSheet sheet) {
		this.sheet = sheet;
	}
	public WritableFont getNormalFont() {
		return normalFont;
	}
	public void setNormalFont(WritableFont normalFont) {
		this.normalFont = normalFont;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getyNum() {
		return yNum;
	}
	public void setyNum(int yNum) {
		this.yNum = yNum;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
