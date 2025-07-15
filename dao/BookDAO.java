package com.data.session_04.dao;

import com.data.session_04.model.Book;
import com.data.session_04.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    // Lấy danh sách tất cả sách
    public List<Book> getAllBooks(){
        List<Book> list = new ArrayList<>();
        String sql = "select * from book";
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setDescription(rs.getString("description"));
                list.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }

    // Thêm sách mới
    public void addBook(Book book){
        String sql = "insert into book (title,author,price,description) values(?,?,?,?)";
        Connection conn = null;

        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setDouble(3, book.getPrice());
            stmt.setString(4,book.getDescription());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }
}
