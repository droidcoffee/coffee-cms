package com.shouhuobao.app.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicallyDataSource extends AbstractRoutingDataSource {

	public static final String EMOVE = "emove";
    public static final String TAKE = "take";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDsType(String dsType) {
        contextHolder.set(dsType);
    }

    public static String getDsType() {
        return contextHolder.get();
    }

    public static void clearDsType() {
        contextHolder.remove();
    }
    
	
    @Override
    protected Object determineCurrentLookupKey() {

        return getDsType();
    }

}
