package db.dao;

import db.DBConnection;
package db.dao;

import db.DBConnection;
import dto.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    public static List<Board> getAllBoards() {
        List<Board> boards = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM board");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("board_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Board board = new Board(id, title, content);
                boards.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boards;
    }
    public static boolean createBoard(Board board) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO board (food_type, title, author, location, content) VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setString(1, board.getFoodType());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getAuthor());
            pstmt.setString(4, board.getLocation());
            pstmt.setString(5, board.getContent());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
