package com.gz.utils;

import org.apache.commons.lang3.StringUtils;


/**
 * <br/>功能: 树形层级计算
 * <br/>版本: 1.0
 * <br/>开发人员: 弓振
 * <br/>创建日期: 2018年4月24日
 * <br/>修改日期: 2018年4月24日
 * <br/>修改列表:
 */
public class LevelUtil {
	
	private static final String ROOT = "0";
	private static final String LEVEL_SEPARATOR = ".";

	public static final String getLevel(String parentLevel,Integer parentId) {
		if(StringUtils.isBlank(parentLevel)) {
			return ROOT;
		}
		return StringUtils.join(parentLevel, LEVEL_SEPARATOR ,parentId);
	}

	public static String getRoot() {
		return ROOT;
	}
	
}
