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
import struts.dao.UserDAO;
import struts.model.Book;
import struts.model.User;

/**
 *
 * @author shadyside
 */
public class Home {

    private List<Book> lstAllBook;

    public Home() {

    }

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        Map session = ActionContext.getContext().getSession();
        User user = (User) session.get("USER");
        if (user == null) {
            return "login";
        }/* else if (!userDAO.checkAdmin(user.getUsername())) {
            return "login";
        } */else {
            BookDAO bookDAO = new BookDAO();
            lstAllBook = bookDAO.getAllBook();
            return "success";
        }
    }

    public List<Book> getLstAllBook() {
        return lstAllBook;
    }

    public void setLstAllBook(List<Book> lstAllBook) {
        this.lstAllBook = lstAllBook;
    }

}
