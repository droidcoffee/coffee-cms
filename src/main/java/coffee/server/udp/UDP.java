package coffee.server.udp;

/**
 * UDP信令相关的常量
 * 
 * @author coffee <br>
 *          2013-6-17下午5:27:05
 */
public interface UDP {
	
	String PRE = "CF:";
	/**
	 * 未知的错误码
	 */
	int CF_1 = -1;
	/**
	 * 成功
	 */
	int CF200 = 200;
	/**
	 * 查找目标主机不存在
	 */
	int CF401 = 401;
	
	//TODO 以下是命令相关的
	/**
	 * 注册
	 */
	int CMD2001 = 2001;
	/**
	 * 查找目标主机IP_PORT
	 */
	int CMD2002 = 2002;
}
