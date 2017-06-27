package com.shouhuobao.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

	/**
	 * 短信充值单价
	 */
	private static String smsPrice;

	public AppConfig() {
	}

	@Value("#{configProperties['app.smsPrice']}")
	public void setSmsPrice(String smsPrice) {
		AppConfig.smsPrice = smsPrice;
	}

	// getter
	public static String getSmsPrice() {
		return smsPrice;
	}
}
