package com.shouhuobao.app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * http工具类
 * 
 * @author coffee<br>
 *         2017年3月5日下午12:16:39
 */
public class HttpClient {
	public static void main(String[] args) {
		get("http://guor.shouhuobao.com/guoer-appserv/userInfo?device=1&version=1&userId=270000");
	}

	public static String get(String httpUrl) {
		try {
			String request = httpUrl;
			URL url = new URL(request);

			// InetSocketAddress addr = new InetSocketAddress("140.143.96.216",80);
			InetSocketAddress addr = new InetSocketAddress("39.134.169.217", 8080);
			java.net.Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, addr); // http 代理

			HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
			conn.setDoOutput(true);
			conn.setConnectTimeout(1000 * 10);
			conn.setReadTimeout(1000 * 10);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setRequestProperty("charset", "utf-8");
			conn.setUseCaches(false);
			// 打印返回的结果
			String result = printResponseData(conn);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(httpUrl);
		return null;
	}

	public static String get(String httpUrl, Map<String, ? extends Object> paramsMap) {
		StringBuilder sb = new StringBuilder();
		for (String key : paramsMap.keySet()) {
			sb.append(key).append("=").append(paramsMap.get(key)).append("&");
		}
		String newUrl = httpUrl + "?" + sb.toString();
		return get(newUrl);
	}

	/**
	 * 标准的post请求
	 * 
	 * @param httpUrl
	 * @param urlParameters
	 *            "param1=a&param2=b&param3=c";
	 */
	public static String post(String httpUrl, String urlParameters) {
		try {
			byte[] postData = urlParameters.getBytes("UTF-8");
			int postDataLength = postData.length;
			String request = httpUrl;
			URL url = new URL(request);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setRequestProperty("charset", "utf-8");
			// conn.setRequestProperty("Accept-Encoding", "gzip");
			// conn.setRequestProperty("Content-Trans-Type", "010");
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
			conn.setUseCaches(false);
			// 写入参数
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(postData);
			out.close();
			// 打印返回的结果
			String result = printResponseData(conn);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(httpUrl + " " + urlParameters);
		return null;
	}

	public static String post(String httpUrl, Map<String, ? extends Object> paramsMap) {
		StringBuilder sb = new StringBuilder();
		for (String key : paramsMap.keySet()) {
			sb.append(key).append("=").append(paramsMap.get(key)).append("&");
		}
		return post(httpUrl, sb.toString());
	}

	/**
	 * 打印返回的数据
	 */
	private static String printResponseData(HttpURLConnection conn) {
		try {
			// 获取 response header
			String contentType = conn.getHeaderField("Content-Type");
			String charset = "utf-8";
			if (contentType != null) {
				charset = contentType.toLowerCase().contains("gbk") ? "gbk" : "utf-8";
			}
			//
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			String result = sb.toString();
			if (result.contains("\\u")) {
				result = result.replace("\\\\u", "\\u");
				result = castLLu2Lu(result);
			}
			System.out.println(result);
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将"\\\\u" 转化为 "\\u"
	 * 
	 * @param source
	 * @return
	 */
	private static String castLLu2Lu(String source) {
		StringBuffer buf = new StringBuffer();
		Matcher m = Pattern.compile("\\\\u([0-9A-Fa-f]{4})").matcher(source);
		while (m.find()) {
			try {
				int cp = Integer.parseInt(m.group(1), 16);
				m.appendReplacement(buf, "");
				buf.appendCodePoint(cp);
			} catch (NumberFormatException e) {
			}
		}
		m.appendTail(buf);
		String result = buf.toString();
		return result;
	}

	/**
	 * 传入绝对路径
	 * 
	 * @param protocolFile
	 */
	public String postFromFile(String protocolFile) {
		// InputStream is =
		// HttpClient.class.getClassLoader().getResourceAsStream(protocolFile);
		try {
			FileInputStream fin = new FileInputStream(protocolFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
			// set host|requestMethod
			String line = nextLine(reader);
			String[] strs = line.split(" ");
			String requestMethod = strs[0];
			String requestUrl = strs[1];

			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 允许Input、Output，不使用Cache
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			// 如果是https请求,则需要特殊处理
			// if (requestUrl.startsWith("https")) {
			// handleHttps(conn);
			// }

			boolean isBody = false;
			StringBuilder body = new StringBuilder();
			// set property
			while ((line = nextLine(reader)) != null) {
				if (isBody == false) {
					if (line.contains(":")) {
						String[] props = line.split(": ");
						if (props.length > 1) {
							conn.setRequestProperty(props[0], props[1]);
						} else {
							conn.setRequestProperty(props[0], "");
						}
					} else if (line.trim().length() == 0) {
						// 跳过第一个空行
						isBody = true;
					}
				} else {
					body.append(line).append("\r\n");
				} // end else
			} // end while
			reader.close();
			fin.close();

			conn.setRequestProperty("Content-Length", String.valueOf(body.toString().trim().length()));

			// post ()
			if ("post".equals(requestMethod.toLowerCase()) && body.toString().trim().length() > 0) {
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				// 注意需要trim一下
				out.write(body.toString().trim().getBytes("utf-8"));
				out.close();
			}
			//
			String result = printResponseData(conn);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unused")
	private void handleHttps(HttpURLConnection conn) throws Exception {
		HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
		X509TrustManager xtm = new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

			}

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

			}
		};

		TrustManager[] tm = { xtm };

		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(null, tm, null);

		httpsConn.setSSLSocketFactory(ctx.getSocketFactory());
		httpsConn.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
	}

	private String nextLine(BufferedReader reader) {
		try {
			String line = reader.readLine();
			if (line != null && line.startsWith("#")) {
				return nextLine(reader);
			}
			return line;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
