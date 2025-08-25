package com.itgroup.dao;

import java.util.ArrayList;
import java.util.List;

import com.itgroup.bean.Book;

import java.sql.*;

public class BookDao extends SuperDao {

    public BookDao() {
    }

    public int insertData(Book book) {
        int result = 0;
        String sql = "insert into books (title, author, publisher, publication_date, number_of_pages, category, price, ebook)" + "values(?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setDate(4, Date.valueOf(book.getPublication_date()));
            pstmt.setInt(5, book.getNumber_of_pages());
            pstmt.setString(6, book.getCategory());
            pstmt.setInt(7, book.getPrice());
            pstmt.setString(8, book.getEbook());

            result = pstmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            super.close(conn, pstmt);
        }
        return result;
    }

    public int getSize() {
        int count = 0;
        String sql = "select count(*) from books";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn, pstmt, rs);

        }
        return count;
    }

    public int updateData(Book book) {
        int result = 0;
        String sql = "update books set title=?, author=?, publisher=?, publication_date=?, number_of_pages=?, category=?, price=?, ebook=? " + "where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setDate(4, Date.valueOf(book.getPublication_date()));
            pstmt.setInt(5, book.getNumber_of_pages());
            pstmt.setString(6, book.getCategory());
            pstmt.setInt(7, book.getPrice());
            pstmt.setString(8, book.getEbook());
            pstmt.setInt(9, book.getId());

            result = pstmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            super.close(conn, pstmt);
        }
        return result;

    }

    public List<Book> selectAll() {
        List<Book> list = new ArrayList<>();
        String sql = "select * from books order by id";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDate("publication_date").toString(),
                        rs.getInt("number_of_pages"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getString("ebook")
                );
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn, pstmt, rs);
        }
        return list;
    }

    public Book getOne(int id) {
        Book book = null;
        String sql = "select * from books where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDate("publication_date").toString(),
                        rs.getInt("number_of_pages"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getString("ebook")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn, pstmt, rs);
        }

        return book;
    }

    public int deleteData(int id) {
        int result = 0;
        String sql = "delete from books where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn, pstmt);
        }
        return result;
    }

    public boolean existsById(int id) {

        String sql = "select count(*) from books where id=? and ebook = 'Y'";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()){
                int count = rs.getInt(1);
                result = (count > 0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn, pstmt, rs);
        }

        return result;
    }

    public List<Book> selectByCategory(String category) {
        List<Book> list = new ArrayList<>();
        String sql = "select * from books where category=? order by id";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDate("publication_date").toString(),
                        rs.getInt("number_of_pages"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getString("ebook")
                );
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn, pstmt, rs);
        }
        return list;
    }

    public List<Book> selectByTitle(String title) {
        List<Book> list = new ArrayList<>();
        String sql = "select * from books where title like ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + title + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book(

                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDate("publication_date").toString(),
                        rs.getInt("number_of_pages"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getString("ebook")
                );
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn, pstmt, rs);
        }

        return list;
    }

 public boolean existsEbookByTitle(String title){
        String sql = "select count(*) from books where title like ? and ebook = 'Y'";
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     boolean result = false;

     try {
         conn = super.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, "%" + title + "%");
         rs = pstmt.executeQuery();

         if (rs.next()) {
             result = rs.getInt(1) > 0;
         }
     }catch (SQLException e) {
         e.printStackTrace();
     }finally {
         super.close(conn, pstmt, rs);
     }

     return  result ;
 }
}

