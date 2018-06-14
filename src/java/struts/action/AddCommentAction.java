/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import struts.dao.BookDAO;
import struts.dao.CommentDAO;
import struts.dao.UserDAO;
import struts.model.Book;
import struts.model.Comment;
import struts.model.User;

/**
 *
 * @author shadyside
 */
public class AddCommentAction {

    private int ID;
    private String content;
    private Book book;
    private List<Comment> lstComment;

    public AddCommentAction() {
    }

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        Map session = ActionContext.getContext().getSession();
        User user = (User) session.get("USER");
        if (user == null) {
            return "login";
            /*        } else if (userDAO.checkAdmin(user.getUsername())) {
//            return "login";
//        } */
        } else {
            int userID = (int) session.get("loginID");
            CommentDAO commentDAO = new CommentDAO();
            BookDAO bookDAO = new BookDAO();
            boolean result = commentDAO.addComent(userID, ID, content);
            book = bookDAO.getBookByID(ID);
            lstComment = commentDAO.getCommentByBookID(ID);
            return "success";
        }
    }

    public int getBookID() {
        return ID;
    }

    public void setBookID(int ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Comment> getLstComment() {
        return lstComment;
    }

    public void setLstComment(List<Comment> lstComment) {
        this.lstComment = lstComment;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
