package server;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

import db.DBConnection;
import db.dao.BoardDAO;
import dto.Board;

public class MainServer extends WebSocketServer {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        final int PORT = 9990;

        WebSocketServer server = new MainServer(new InetSocketAddress(host, PORT));
        server.run();
    }

    public MainServer(InetSocketAddress inetAddr) {
        super(inetAddr);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(conn + "연결이 끊겼습니다.");
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println(ex.getMessage());
        ex.printStackTrace();
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("메시지 수신: " + message);

        JSONObject msgObj = new JSONObject(message);
        String cmd = msgObj.getString("cmd");

        if (cmd.equals("join")) {
            // 회원가입 로직 추가
        } else if (cmd.equals("login")) {
            // 로그인 로직 추가
        } else if (cmd.equals("allchat")) {
            // 채팅 로직 추가
        } else if (cmd.equals("getBoards")) {
            // 게시판 목록 가져오기
            List<Board> boards = BoardDAO.getAllBoards();
            JSONObject response = new JSONObject();
            response.put("cmd", "getBoards");
            response.put("boards", boards);
            conn.send(response.toString());
        } else if (cmd.equals("createBoard")) {
            // 새로운 게시글 작성
            String foodType = msgObj.getString("foodType");
            String title = msgObj.getString("title");
            String author = msgObj.getString("author");
            String location = msgObj.getString("location");
            String content = msgObj.getString("content");

            Board newBoard = new Board(foodType, title, author, location, content);
            if (BoardDAO.createBoard(newBoard)) {
                JSONObject response = new JSONObject();
                response.put("cmd", "createBoard");
                response.put("success", true);
                conn.send(response.toString());
            } else {
                JSONObject response = new JSONObject();
                response.put("cmd", "createBoard");
                response.put("success", false);
                conn.send(response.toString());
            }
        }
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        String hostIp = conn.getRemoteSocketAddress().getAddress().getHostAddress().toString();
        System.out.println(hostIp + " 님이 연결되었습니다.");

        JSONObject ackObj = new JSONObject();
        ackObj.put("cmd", "connect");
        ackObj.put("result", "연결 성공 !!!");
        conn.send(ackObj.toString());
    }

    @Override
    public void onStart() {
        DBConnection.open();
        System.out.println("BitBox 서버 실행 !!!");
    }

    @Override
    public void stop() throws InterruptedException {
        DBConnection.close();
        super.stop();
        System.out.println("BitBox 서버 종료 !!!");
    }
}
