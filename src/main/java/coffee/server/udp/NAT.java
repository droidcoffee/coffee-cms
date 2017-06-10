package coffee.server.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author coffee <br>
 *         2013-6-13上午11:05:37
 */
public class NAT {
	static final int PORT = 9000;
	static final String SERVER = "110.76.45.9";//110.76.45.9
	private DatagramSocket dataSocket;

	/**
	 * key:设备的唯一标识 value:设备的
	 */
	private static Map<String, String> natMap = new HashMap<String, String>();

	public NAT() {
		start();
	}

	public void start() {
		try {
			dataSocket = new DatagramSocket(PORT);
			byte[] receiveByte = new byte[1024];
			DatagramPacket dataPacket = new DatagramPacket(receiveByte,
					receiveByte.length);
			String receiveStr = "";
			int len = -1;
			while (true) {
				dataSocket.receive(dataPacket);
				len = dataPacket.getLength();
				// 接收数据
				if (len > 0) {
					// 指定接收到数据的长度,可使接收数据正常显示,开始时很容易忽略这一点
					receiveStr = new String(receiveByte, 0,
							dataPacket.getLength());
					int cmd = UDPParser.getCmdCode(receiveStr);
					// 要返回的数据
					String retData = "";
					// 注册
					switch (cmd) {
					case UDP.CMD2001:
						retData = reg(receiveStr, dataPacket);
						break;
					case UDP.CMD2002:
						retData = findTarget(receiveStr);
						break;
					}

					System.out.println(receiveStr + " --- "
							+ dataPacket.getAddress() + "---"
							+ dataPacket.getPort());
					System.out.println("<<<<<<" + retData);
					// 返回数据
					byte[] buf = retData.getBytes();
					dataSocket.send(new DatagramPacket(buf, buf.length,
							dataPacket.getAddress(), dataPacket.getPort()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册 >> CF:2001:ID << CF:200
	 * 
	 * @param receiveStr
	 * @param dataPacket
	 */
	public String reg(String receiveStr, DatagramPacket dataPacket) {
		System.out.println("new reg >> " + receiveStr);
		String id = UDPParser.getLastSegment(receiveStr);
		if (id != null && !"".equals(id.trim())) {
			String addr = dataPacket.getAddress() + "_" + dataPacket.getPort();
			natMap.put(id, addr);
		}
		return UDPParser.getRetPre(UDP.CMD2001);
	}

	/**
	 * 查找目标主机的ip和端口号<br>
	 * >> CF:2002:ID<br>
	 * << CF:200:ip_port
	 * 
	 * @param receiveStr
	 * @return
	 */
	public String findTarget(String receiveStr) {
		System.out.println("查找目标ID >> " + receiveStr);
		String to = UDPParser.getLastSegment(receiveStr);
		String destIp = natMap.get(to);
		String retStr = "";
		if (destIp == null) {
			destIp = String.valueOf(UDP.CF401);
		} else {
			retStr = destIp;
		}
		return UDPParser.getRetPre(UDP.CMD2002) + ":" +retStr;
	}

	public static void main(String args[]) {
		new NAT();
	}
}
