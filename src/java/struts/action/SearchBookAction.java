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
public class SearchBookAction {

    private String nameSearch;
    private List<Book> lstBook;

    public SearchBookAction() {
    }

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        Map session = ActionContext.getContext().getSession();
        User user = (User) session.get("USER");
        if (user == null) {
            return "login";
        }/* else if (!userDAO.checkAdmin(user.getUsername())) {
            return "login";
        }*/ else {
            BookDAO bookDAO = new BookDAO();
            lstBook = bookDAO.searchBook(nameSearch);
            if (lstBook.size() > 0) {
                return "success";
            } else {
                return "fail";
            }
        }
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public List<Book> getLstBook() {
        return lstBook;
    }

    public void setLstBook(List<Book> lstBook) {
        this.lstBook = lstBook;
    }

}
