package coffee.front.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 该类是Servlet核心的相关操作 非public防止其他非core包下的类去继承该类
 * 
 * @author coffee<br>
 *         2013-4-13上午9:46:51
 */
public abstract class CoreServlet extends HttpServlet {
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
		doService(request, response);
	}

	protected abstract void doService(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doService(req, resp);
	}

	/**
	 * 
	 * @param response
	 * @param data
	 */
	protected void writeData(HttpServletResponse response, String data) {
		try {
//			PrintWriter out = response.getWriter();
//			out.write(data);
//			out.write("\n\r");
//			out.flush();
			System.out.println(data+"\n\r");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		// Log.info(CoreServlet.class, "POST_DATA>> " + sb.toString());
		return sb.toString();
	}

	public String getEncodedString(String requestValue) {
		if (requestValue == null) {
			return null;
		}
		try {
			requestValue = new String(requestValue.getBytes("iso_8859_1"),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return requestValue;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	public boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

}
