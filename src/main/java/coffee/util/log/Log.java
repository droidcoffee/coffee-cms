package coffee.util.log;

public class Log {
	/**
	 * 日志是否开启的总开关
	 */
	private static final boolean OPEN = true;
	public static void info(Object thiz, String msg) {
		if (OPEN) {
			if (thiz instanceof Class) {
				//handleLogger((Class<?>) thiz).info(msg);
			} else {
				//handleLogger(thiz.getClass()).info(msg);
			}
			System.out.println(msg);
		}
	}

	public static void warn(Object thiz, String msg) {
		if (OPEN) {
			if (thiz instanceof Class) {
				//handleLogger((Class<?>) thiz).warning(msg);
			} else {
				//handleLogger(thiz.getClass()).warning(msg);
			}
			System.out.println(msg);
		}
	}

	public static void error(Object thiz, String msg) {
		if (OPEN) {
			//handleLogger(thiz.getClass()).severe(msg);
			System.out.println(msg);
		}
	}
	
	public static void error(Object thiz, String msg, Exception e) {
		if (OPEN) {
			//handleLogger(thiz.getClass()).severe(msg);
		}
	}
}
