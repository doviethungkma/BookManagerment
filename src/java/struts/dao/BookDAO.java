/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import struts.model.Book;

/**
 *
 * @author lanth
 */
public class BookDAO {

    public List<Book> getAllBook() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        DataAccess da = new DataAccess();
        conn = da.getConnection();
        String sql = "Select * from tbl_book ORDER BY ID DESC";
        List<Book> lstAllBook = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                String description = rs.getString("Description");
                book.setID(rs.getInt("ID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setDescription(cutDescription(description));
                book.setImage(rs.getString("Image"));
                lstAllBook.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstAllBook;
    }

 public List<Book> getAllBook1() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        DataAccess da = new DataAccess();
        conn = da.getConnection();
        String sql = "Select * from tbl_book ORDER BY ID DESC";
        List<Book> lstAllBook = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                String description = rs.getString("Description");
                book.setID(rs.getInt("ID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setDescription(cutDescription(description));
                book.setImage(rs.getString("Image"));
                lstAllBook.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstAllBook;
    }

    public Book getBookByID(int ID) {
        DataAccess da = new DataAccess();
        Book book = new Book();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet rs;

        conn = da.getConnection();
        String sql = "Select * from tbl_book where ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                book.setID(rs.getInt("ID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setDescription(rs.getString("Description"));
                book.setImage(rs.getString("Image"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }
//

    public boolean deleteBook(int ID) {
        DataAccess da = new DataAccess();
        Connection conn = conn = da.getConnection();
        PreparedStatement ps;

        try {
            //Xóa các comment trên book
            String sql = "Delete from tbl_comment where BookID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ps.executeUpdate();
            String sql1 = "Delete from tbl_book where ID = ?";
            try {
                ps = conn.prepareStatement(sql1);
                ps.setInt(1, ID);
                if (ps.executeUpdate() > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
//

    public boolean addBook(String title, String author, double price, String description, String image) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        String sql = "INSERT INTO tbl_book (Title, Author, Price, Description, Image) VALUE (?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setString(5, image);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
//

    public boolean updateBook(int ID, String title, String author, double price, String description, String image) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        String sql = "UPDATE tbl_book "
                + "SET Title = ?, Author = ?, Price = ?, Description = ?, Image = ? "
                + "WHERE ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setString(5, image);
            ps.setInt(6, ID);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
//

    public List<Book> searchBook(String nameSearch) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * "
                + "FROM tbl_book "
                + "WHERE Title LIKE '%" + nameSearch + "%' ";
        List<Book> lstBookSearch = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
//            ps.setString(1, nameSearch);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setID(rs.getInt("ID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getDouble("Price"));
                book.setDescription(rs.getString("Description"));
                book.setImage(rs.getString("Image"));
                lstBookSearch.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstBookSearch;
    }

    public static String cutDescription(String description) {
        int len = description.length();
        String result = "";
        if (description.length() > 250) {
            result = description.substring(0, 250);
        }
        return result;
    }

//    public static void main(String[] args) {
//        String abc = "Doviethung\n"
//                + "sdjahkshd";
//        System.out.println(abc.substring(6, abc.length()));
//    }
}
