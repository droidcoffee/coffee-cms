package coffee.wechat.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffee.util.log.Log;

/**
 * 该类是Servlet核心的相关操作 非public防止其他非core包下的类去继承该类
 * 
 * @author coffee 20122012-12-10下午10:47:12
 */
abstract class CoreServlet extends HttpServlet {
	static final long serialVersionUID = 1L;

	/**
	 * 当前请求的request对象
	 */
	protected HttpServletRequest request = null;

	public CoreServlet() {
		// 目前该方式用Log代替
		// Outer.setPath(null, true, false);
	}

	protected final void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 调用子类的doPost
		doPost(request, response);
	}

	/**
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	public String getPostData(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		br.close();
		Log.info(CoreServlet.class, "POST_DATA>> " + sb.toString());
		return sb.toString();
	}

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}

}
