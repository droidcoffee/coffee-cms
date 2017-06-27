package com.shouhuobao.app.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
@Component
public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice, Ordered {

	private Logger logger = LoggerFactory.getLogger(DataSourceAdvice.class);

	@Override
	public void afterReturning(Object arg0, Method method, Object[] arg2, Object arg3) throws Throwable {

	}

	@Override
	public void before(Method method, Object[] arg1, Object target) throws Throwable {
		if (method.getDeclaringClass().getName().startsWith("com.shouhuobao.take")) {
			// logger.info("select: analyze");
			DynamicallyDataSource.setDsType(DynamicallyDataSource.TAKE);
		} else {
			// logger.info("select: emove");
			DynamicallyDataSource.setDsType(DynamicallyDataSource.EMOVE);
		}
	}

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		ex.printStackTrace();
		logger.error("-----error", ex.getMessage());
	}

	public int getOrder() {
		return 2;
	}

	// @Pointcut("execution(public * com.shouhuobao.take.service.*.*(..))")
	// public void take() {
	// logger.info("xxx");
	// };
	//
	// @Before("take()")
	// public void before(JoinPoint jp) {
	// DataSourceContextHolder.setDsType(DataSourceContextHolder.TAKE);
	// }
}
