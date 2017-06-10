package com.cms.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shouhuobao.app.LogInterceptor;
/**
 * 登录拦截器
 * @author coffee<br>2017年5月20日下午12:33:50
 */
public class CmsInterceptor extends LogInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}
}
