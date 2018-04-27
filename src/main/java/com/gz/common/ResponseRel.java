package com.gz.common;

import lombok.ToString;

import com.gz.common.constant.ResponseRelInfo;

/**
 * <br/>功能: 接口响应规范
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月22日
 * <br/>修改日期: 2018年4月22日
 * <br/>修改列表:
 */
@SuppressWarnings("unused")
public class ResponseRel {

	private String rtnMsg;
	private Integer rtnCode;
	private Object data;
	
	public ResponseRel(ResponseRelInfo reponseRelMsg) {
		this.rtnMsg = reponseRelMsg.getValue();
		this.rtnCode = reponseRelMsg.getCode();
	}
	
	public static ResponseRel success(Object data) {
		ResponseRel jsonData = new ResponseRel(ResponseRelInfo.SUCCESS);
		jsonData.data = data;
		return jsonData;
	}
	
	public static ResponseRel fail(Object data) {
		ResponseRel jsonData = new ResponseRel(ResponseRelInfo.FAIL);
		jsonData.data = data;
		return jsonData;
	}
	
	public static ResponseRel cusRel(ResponseRelInfo responseRelMsg, Object data) {
		ResponseRel jsonData = new ResponseRel(responseRelMsg);
		jsonData.data = data;
		return jsonData;
	}

	public String getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

	public Integer getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(Integer rtnCode) {
		this.rtnCode = rtnCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseRel [rtnMsg=" + rtnMsg + ", rtnCode=" + rtnCode
				+ ", data=" + data + "]";
	}
	
}
