package com.gz.common.constant;


/**
 * <br/>功能: 响应结果消息
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月22日
 * <br/>修改日期: 2018年4月22日
 * <br/>修改列表:
 */
public enum ResponseRelInfo {

	SUCCESS(0,"成功"),
	FAIL(-9999,"失败");
	
	private Integer code;
	private String value;
	private ResponseRelInfo(Integer code, String value) {
		this.code = code;
		this.value = value;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
