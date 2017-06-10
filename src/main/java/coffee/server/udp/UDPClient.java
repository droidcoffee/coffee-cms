package coffee.server.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 
 * @author coffee <br>
 *         2013-6-13上午11:05:49
 */
public class UDPClient {
	private static int PORT = 14562;
	private DatagramSocket dataSocket;
	// 当前客户端的唯一id
	private String id = "";
	//
	private String targetIp;
	private String targetPort;

	public UDPClient(String id) {
		this.id = id;
		try {
			System.out.println("绑定端口-------------" + PORT);
			dataSocket = new DatagramSocket(PORT + 1);
			//
			new Thread(new Runnable() {
				@Override
				public void run() {
					startRecv();
				}
			}).start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动线程，接收服务器段数据
	 */
	public void startRecv() {
		DatagramPacket recvPacket = new DatagramPacket(new byte[1024], 1024);
		try {
			int len = -1;
			while (true) {
				dataSocket.receive(recvPacket);
				len = recvPacket.getLength();
				if (len > 0) {
					byte[] tmp = recvPacket.getData();
					String receiveStr = new String(tmp, 0,
							recvPacket.getLength());
					int cmd = UDPParser.getCmdCode(receiveStr);
					// String ipPort = UDPParser.getLastSegment(recvStr);
					System.out.println(">>>>>>[" + cmd + "]" + receiveStr
							+ " --- " + recvPacket.getAddress() + ":"
							+ recvPacket.getPort());
					// 注册
					switch (cmd) {
					case UDP.CMD2001:
						break;
					case UDP.CMD2002:
						// /127.0.0.1
						String str = UDPParser.getLastSegment(receiveStr);
						this.targetIp = str.substring(1, str.indexOf("_"));
						this.targetPort = str.substring(str.indexOf("_") + 1);
						break;
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reg() {
		try {
			String sendStr = UDP.PRE + UDP.CMD2001 + ":" + this.id;
			// 指定端口号，避免与其他应用程序发生冲突
			byte[] data = sendStr.getBytes();
			DatagramPacket sendData = new DatagramPacket(data, data.length,
					InetAddress.getByName(NAT.SERVER), NAT.PORT);
			dataSocket.send(sendData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过NAT先从服务器端获取目标主机的iP
	 * 
	 * @param destId
	 *            目标主机的IP
	 * 
	 * @return
	 */
	public String getDestIp(String destId) {
		try {
			System.out.println("查找目标主机IP" + destId);
			String sendStr = UDP.PRE + UDP.CMD2002 + ":" + destId;
			// 指定端口号，避免与其他应用程序发生冲突
			byte[] data = sendStr.getBytes();
			DatagramPacket sendData = new DatagramPacket(data, data.length,
					InetAddress.getByName(NAT.SERVER), NAT.PORT);
			dataSocket.send(sendData);
		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return null;
	}

	public void sendMessage(String content) {
		try {
			System.out.println("发送消息 >> " + content);
			System.out.println(this.targetIp + "---" + this.targetPort);
			byte[] data = content.getBytes();
			DatagramPacket sendData = new DatagramPacket(data, data.length,
					InetAddress.getByName(this.targetIp),
					Integer.parseInt(this.targetPort));
			dataSocket.send(sendData);
		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public static void main(String args[]) throws InterruptedException {
		args = new String[]{"aaa"};
		if ("aaa".equals(args[0])) {
			UDPClient aa = new UDPClient("aaa");
			aa.reg();
			Thread.sleep(1000 * 3);
			aa.getDestIp("bbb");
			Thread.sleep(1000 * 5);
			while (true) {
				aa.sendMessage("aa --> bb " + System.currentTimeMillis());
				Thread.sleep(1000 * 10);
			}
		} else if ("bbb".equals(args[0])) {
			PORT = PORT + 5;
			UDPClient bb = new UDPClient("bbb");
			bb.reg();
			bb.getDestIp("aaa");
			Thread.sleep(1000 * 10);
			while (true) {
				bb.sendMessage("bb --> aa " + System.currentTimeMillis());
				Thread.sleep(1000 * 10);
			}
		}
	}
}