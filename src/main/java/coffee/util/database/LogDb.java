package coffee.util.database;

import java.util.Formatter;

/*
 * 代理类
 */
public class LogDb {

	public static void w(Object tag, String msg) {
		coffee.util.log.Log.warn("DB_" + tag, msg);
	}

	public static void e(Object tag, String msg) {
		coffee.util.log.Log.error(tag, msg);
	}

	public static void i(Object tag, String msg) {
		coffee.util.log.Log.info(tag, msg);
	}

	public static void sql(Object tag, String sql, Object... args) {
		try {
			if (args == null || args.length == 0) {
				coffee.util.log.Log.info("SQL_" + tag, sql);
			} else {
				String info = sql.replaceAll("\\?", "%s");
				info = String.format(info, args);
				coffee.util.log.Log.info("SQL_" + tag, info);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String ss = String.format("%s %s", new Object[] { "123", "123" });
		System.out.println(ss);

		sql("xx", "? ? ?", new Object[] {});
	}
}
