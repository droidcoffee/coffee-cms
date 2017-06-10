package coffee;

public class Config {
	
	/**
	 * 本机主机名
	 */
	public static final String XMPP_HOST = "android";

	public static boolean DEBUG = true;

	public static String HOST = "http://42.121.114.146/";
	/**
	 * 是否开启TOKEN的验证
	 * http://42.121.114.146/droidcoffee/DingFanServlet
	 * ?signature=af3137eadb5105da7f33dcc56fc7bac7991c8d38&timestamp=1354965035&nonce=1354839770
	 */
	public static boolean VALID_TOKEN = false;
	
	/**
	 * 注意conf目录不能作为src路径 需要 Build Path --> exclude
	 */
	public static final String CMS_MENU_PROPS = "conf/admin_menu";
	public static final String DB_JDBC_PROPS = "conf/jdbc";
	
	public static final String LOG_DIR = "/root/logs";
	public static final String TOMCAT_HOME = "/usr/local/tomcat/apache-tomcat-7.0.33/webapps/droidcoffee/";
}
