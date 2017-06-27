package com.shouhuobao.app.datasource;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;

public class SqlSessionFactoryBeanExt extends SqlSessionFactoryBean {

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		SqlSessionFactory sqlSessionFactory = super.buildSqlSessionFactory();
		org.apache.ibatis.session.Configuration  config = sqlSessionFactory.getConfiguration();
		TypeHandlerRegistry typeHandlerRegistry = config.getTypeHandlerRegistry();
		typeHandlerRegistry.register(String.class, StringTypeHandler.class);
//		typeHandlerRegistry.register(long.class, MyBatisDateHandler.class);
//		typeHandlerRegistry.register(long.class, JdbcType.TIMESTAMP, MyBatisDateHandler.class);
		// typeHandlerRegistry.register(JdbcType.TIMESTAMP, new MyBatisDateHandler());

		return sqlSessionFactory;
	}

}
