package coffee.server.udp;

import java.text.MessageFormat;

/**
 * 协议的解析类
 */
public class UDPParser {

	
	public static void main(String[] args) {
		System.out.println(getRetPre(UDP.CMD2001));
	}
	
	public static String getRetPre(int cmd, int... defCode){
		String ret = UDP.PRE + "{0}:{1}";
		if(defCode.length == 0){
			ret = MessageFormat.format(ret, cmd + "", 200 + "");
		}else{
			ret = MessageFormat.format(ret, cmd + "", defCode[0] + "");
		}
		return ret;
	}
	/**
	 * 获取命令码 CF:200
	 * 
	 * @return
	 */
	public static int getCmdCode(String data) {
		try {
			String code = data.substring(UDP.PRE.length(),
					data.indexOf(":", UDP.PRE.length()));
			int retCode = Integer.valueOf(code);
			return retCode;
		} 
		catch(StringIndexOutOfBoundsException e){
			//ignore
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * CF:2001:ID
	 * 
	 * @return
	 */
	public static String getLastSegment(String data) {
		try {
			String id = data
					.substring(data.lastIndexOf(":") + 1, data.length());
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
