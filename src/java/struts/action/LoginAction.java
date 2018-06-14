/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import struts.dao.UserDAO;
import struts.model.User;

/**
 *
 * @author lanth
 */
public class LoginAction {

    private String username;
    private String password;
    private List<User> lstAllUser;
    private User user;

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        int loginID = userDAO.checkLogin(username, password);
        String result = "";
        if (loginID > 0) {
            user = userDAO.getUserByID(loginID);
            Map session = ActionContext.getContext().getSession();
            session.put("LoginID", loginID);
            session.put("USER", user);
            boolean checkAdmin = userDAO.checkAdmin(loginID);
            if (checkAdmin) {
                lstAllUser = userDAO.getAllUser();
                result = "successAdmin";
            } else {
                return "successCustomer";
            }
        } else {
            result = "fail";
        }
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getLstAllUser() {
        return lstAllUser;
    }

    public void setLstAllUser(List<User> lstAllUser) {
        this.lstAllUser = lstAllUser;
    }

    public LoginAction() {
    }

    public LoginAction(String username, String password, List<User> lstAllUser) {
        this.username = username;
        this.password = password;
        this.lstAllUser = lstAllUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
