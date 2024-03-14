package websocket;


import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

public class SimpleWebSocketServer extends WebSocketServer{
	
	public static void main(String[] args) {
		String host = "127.0.0.1";		// localhost
		final int PORT = 9000;
		
		WebSocketServer server = new SimpleWebSocketServer(new InetSocketAddress(host, PORT));
		server.run();
	}
	
	public SimpleWebSocketServer(InetSocketAddress inetAddr) {
		super(inetAddr);
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println(conn + " has diconnected");		
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.out.println(ex.getMessage());
		//ex.getStackTrace();
	}

	@Override
	public void onMessage(WebSocket arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		String hostIp = conn.getRemoteSocketAddress().getAddress().getHostAddress().toString();
		System.out.println(hostIp + " connected");
		
		JSONObject ackObj = new JSONObject();
		ackObj.put("cmd", "connect");
		ackObj.put("result", "Welcome to the Server!");
		conn.send(ackObj.toString());   // 클라이언트한테 메시지 보내기
	}

	@Override
	public void onStart() {
		System.out.println("Server started successfully!!!");		
	}

}