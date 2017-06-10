package com.cms.token;

import com.framework.libcore.cache.AppCache;

/**
 * token管理类
 * 
 * @author coffee<br>
 * 		2017年5月20日下午12:41:22
 */
public class TokenCache {
	/**
	 * 刷新token
	 * 
	 * @param userId
	 */
	public static void update(Integer userId) {

	}

	/**
	 * 添加用户token
	 * 
	 * @param userId
	 * @param token
	 */
	public static void add(Integer userId, String token) {
		AppCache.getInstance().put(String.valueOf(userId), token, 1000 * 60 * 15);
	}

	/**
	 * 获取token
	 * 
	 * @param userId
	 * @return
	 */
	public static String getToken(Integer userId) {
		return AppCache.getInstance().getString(String.valueOf(userId));
	}
}
