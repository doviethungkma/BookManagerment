/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import struts.dao.BookDAO;
import struts.dao.UserDAO;
import struts.model.Book;
import struts.model.User;

/**
 *
 * @author shadyside
 */
public class RedirectEditBookAction {

    private int ID;
    private Book book;

    public RedirectEditBookAction() {
    }

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        Map session = ActionContext.getContext().getSession();
//        User user = (User) session.get("USER");
        int loginID = (int) session.get("loginID");
        if (loginID == 0) {
            return "login";
        } else if (userDAO.checkAdmin(loginID) == false) {
            return "login";
        } else {
            BookDAO bookDAO = new BookDAO();
            book = bookDAO.getBookByID(ID);
            if (book.getID() > 0) {
                return "success";
            } else {
                return "fail";
            }
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
