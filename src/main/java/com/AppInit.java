package com;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * app配置初始化--服务器启动的时候会执行一次
 * 
 * @author coffee<br>
 *         2017年5月15日下午8:07:51
 */
@Component
public class AppInit {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void init() {
		logger.info("app logger");
	}
}
