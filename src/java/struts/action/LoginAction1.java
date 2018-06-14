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
public class LoginAction1 {

    private String username;
    private String password;
    private int loginFailCount;
    private List<User> lstAllUser;
    private String error;

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        Map session = ActionContext.getContext().getSession();
        int loginID = userDAO.checkLogin(username, password);

        if (loginID == 0) {
            if (session.get("loginFailCount") == null) {
                loginFailCount = 0;
            } else {
                loginFailCount = (int) session.get("loginFailCount");
            }
            if (loginFailCount > 5) {
                error = "Nhập sai tên đăng nhập hoặc mật khẩu!!";
                return "captcha";
            } else {
                loginFailCount++;
                session.put("loginFailCount", loginFailCount);
                error = "Nhập sai tên đăng nhập hoặc mật khẩu!!";
                return "login";
            }
        } else {
            User user = userDAO.getUserByID(loginID);
            boolean checkAdmin = userDAO.checkAdmin(loginID);
            if (checkAdmin) {
                session.put("loginID", loginID);
                session.put("USER", user);
                session.put("username", user.getUsername());
                session.put("role", "Admin");
                lstAllUser = userDAO.getAllUser();
                return "successAdmin";
            } else {
                session.put("loginID", loginID);
                session.put("USER", user);
                session.put("username", user.getUsername());
                session.put("role", "Customer");
                return "successCustomer";
            }
        }
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

    public int getLoginFailCount() {
        return loginFailCount;
    }

    public void setLoginFailCount(int loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    public LoginAction1(String username, String password, int loginFailCount, List<User> lstAllUser) {
        this.username = username;
        this.password = password;
        this.loginFailCount = loginFailCount;
        this.lstAllUser = lstAllUser;
    }

    public LoginAction1() {
    }

    public List<User> getLstAllUser() {
        return lstAllUser;
    }

    public void setLstAllUser(List<User> lstAllUser) {
        this.lstAllUser = lstAllUser;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
