package coffee.util.servlet;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import javax.servlet.http.HttpServletRequest;

import coffee.cms.core.action.Action;

public class RequestUtils {

	/**
	 * 获取客户端ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 将Action中的属性以及其值保存到request的attribute中
	 * 
	 * @param thiz
	 *            : 当前正在执行的action对象
	 * @param request
	 *            : action中【当前】的request请求
	 */
	public static void setAttribute(Action thiz, HttpServletRequest request) {
		try {
			BeanInfo bi = Introspector.getBeanInfo(thiz.getClass(),
					Action.class);
			PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (PropertyDescriptor prop : props) {
				try {
					/**
					 * 如果该Action中存在read方法；则
					 */
					if (prop.getReadMethod() != null) {
						Object value = prop.getReadMethod().invoke(thiz,
								new Object[] {});
						if (value != null) {
							request.setAttribute(prop.getName(), value);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		}
	}

}
