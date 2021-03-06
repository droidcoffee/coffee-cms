package coffee.util.database.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import coffee.util.database.Pager;
import coffee.util.database.Session;
import coffee.util.database.core.DBUtils;

/**
 * 数据同步工具
 * 
 * @author coffee
 * 
 *         2013-1-5 上午9:46:11
 */
public class DataSync {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public DataSync() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param beanClaess
	 */
	public void start(Class<?> beanClaess) {
		DataSync sync = new DataSync();
		Connection fromConn = sync.getConnection("droidcoffee.com", "wechat",
				"coffee", "droid");

		Connection toConn = sync.getConnection("localhost", "wechat", "root",
				"");

		Session fromSession = new Session(fromConn);
		Session toSession = new Session(toConn);

		String tableName = DBUtils.getTableName(beanClaess);
		try {
			int count = fromSession.queryForColumn(Integer.class,
					"select count(*) from " + tableName);
			count = count % 100 == 0 ? count / 100 : count / 100 + 1;
			for (int i = 0; i < count; i++) {
				Pager<?> pager = fromSession.queryForPager("select * from "
						+ tableName, i * 100, 100, beanClaess);
				System.out.println("正在同步从" + i * 100);
				toSession.insert(pager.getItems());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fromSession.close();
			toSession.close();
		}
		System.out.println("data sync ok");
	}

	public Connection getConnection(String host, String database,
			String dbUsername, String dbPassword) {
		String dbUrl = "jdbc:mysql://"
				+ host
				+ ":3306/"
				+ database//
				+ "?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
		try {
			Connection conn = DriverManager.getConnection(dbUrl, dbUsername,
					dbPassword);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
