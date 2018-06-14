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
public class DeleteBookAction {

    private int ID;
    private List<Book> lstBook;

    public DeleteBookAction() {
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
            boolean result = bookDAO.deleteBook(ID);

            if (result) {
                lstBook = bookDAO.getAllBook();
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

    public List<Book> getLstBook() {
        return lstBook;
    }

    public void setLstBook(List<Book> lstBook) {
        this.lstBook = lstBook;
    }

}
