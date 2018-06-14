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
import struts.model.Comment;

/**
 *
 * @author shadyside
 */
public class CommentDAO {

    public boolean addComent(int userID, int bookID, String content) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "INSERT INTO tbl_comment (UserID, BookID, Content, Datecreated) "
                + "VALUE (?,?,?,CURRENT_TIME)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, bookID);
            ps.setString(3, content);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<Comment> getCommentByBookID(int BookID) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM tbl_comment, tbl_user"
                + " WHERE BookID = ? AND tbl_comment.UserID = tbl_user.ID"
                + " ORDER BY DateCreated DESC";
        List<Comment> lstComment = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, BookID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setUsername(rs.getString("Username"));
                comment.setUserImage(rs.getString("Avatar"));
                comment.setBookID(rs.getInt("BookID"));
                comment.setUserID(rs.getInt("UserID"));
                comment.setContent(rs.getString("Content"));
                comment.setDateCreated(rs.getString("DateCreated"));
                lstComment.add(comment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstComment;
    }

//    public static void main(String[] args) {
//        CommentDAO commentDAO = new CommentDAO();
//        List<Comment> lstComment = commentDAO.getCommentByBookID(6);
//        for (Comment comment : lstComment) {
//            System.out.println(comment.getContent());
//        }
//
//    }
}
