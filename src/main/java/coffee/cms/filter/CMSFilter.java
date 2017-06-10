package coffee.cms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coffee.cms.core.Constants;
import coffee.cms.core.bean.UserBean;

/**
 * Servlet Filter implementation class CMSFilter
 */
@WebFilter(urlPatterns = "/*")
public class CMSFilter implements Filter {

	// 管理员目录的前缀
	private String PREFIX_ADMIN_URI = null;
	// 系统日志
	private String PREFIX_LOG_URI = null;

	public CMSFilter() {

	}

	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 对Form参数进行编码，主要该编码主要对post方式提交的表单有效
		// 如果是get方式提交的表单，则 需要通过new String(value.getByte("iso_8859_1"),"utf-8")
		try {
			// request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpServletRequest req = (HttpServletRequest) request;

		String reqURI = req.getRequestURI();
		// 如果访问admin或者是目录 并且后缀是
		if (reqURI.startsWith(PREFIX_ADMIN_URI)
				|| reqURI.startsWith(PREFIX_LOG_URI)) {
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession(true);
			// 从session里取的用户名信息
			UserBean user = (UserBean) session
					.getAttribute(Constants.KEY_SESSION_USER);
			// 判断如果没有取到用户信息,就跳转到登陆页面
			if (user == null) {
				// 跳转到登陆页面
				res.sendRedirect("http://" + req.getHeader("Host")
						+ req.getContextPath() + "/login.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 初始化系统变量
		PREFIX_ADMIN_URI = fConfig.getServletContext().getContextPath()
				+ "/admin";

		PREFIX_LOG_URI = fConfig.getServletContext().getContextPath() + "/logs";
	}

}
