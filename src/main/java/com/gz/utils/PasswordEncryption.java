package com.gz.utils;

import org.apache.commons.codec.digest.Md5Crypt;

/**
 * <br/>功能: 密码加密
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月25日
 * <br/>修改日期: 2018年4月25日
 * <br/>修改列表:
 */
public class PasswordEncryption {

	private static final String SALT = "g.z!@#GZ";
	/**
	 * @Description: 加密
	 * @param source 加密前的密码
	 * @return String  加密后的密码
	 */
	public static String encry(String source) {
		return Md5Crypt.md5Crypt(source.getBytes(), SALT);
	}
}
