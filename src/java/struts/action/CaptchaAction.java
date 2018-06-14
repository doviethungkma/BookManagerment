/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.action;

import com.captcha.botdetect.web.servlet.Captcha;
import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import struts.dao.UserDAO;
import struts.model.User;

/**
 *
 * @author shadyside
 */
public class CaptchaAction {

    private String username;
    private String password;
    private String captchaCode;
    private List<User> lstAllUser;
    private String error;

    public CaptchaAction() {
    }

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        Map session = ActionContext.getContext().getSession();
        int loginID = userDAO.checkLogin(username, password);
        int loginFailCount = (int) session.get("loginFailCount");
        if (loginID > 0 && validateCaptcha()) {
            User user = new UserDAO().getUserByID(loginID);
            boolean checkAdmin = userDAO.checkAdmin(loginID);
            if (checkAdmin) {
                lstAllUser = userDAO.getAllUser();
                session.put("loginID", loginID);
                session.put("USER", user);
                session.put("username", user.getUsername());
                session.put("role", "Admin");
                return "successAdmin";
            } else {
                session.put("loginID", loginID);
                session.put("USER", user);
                session.put("username", user.getUsername());
                session.put("role", "Customer");
                return "successCustomer";
            }
        } else if (!validateCaptcha()) {
            loginFailCount++;
            session.put("loginFailCount", loginFailCount);
            if (loginFailCount > 10) {
                return "fail";
            } else {
                error = "Sai captcha!!";
                return "captcha";
            }
        } else {
            loginFailCount++;
            session.put("loginFailCount", loginFailCount);
            if (loginFailCount > 10) {
                return "fail";
            } else {
                error = "Nhập sai tên đăng nhập hoặc mật khẩu!!";
                return "captcha";
            }
        }
    }

    public boolean validateCaptcha() {
        Captcha captcha = Captcha.load(ServletActionContext.getRequest(), "exampleCaptcha");
        boolean isHuman = captcha.validate(captchaCode);
        if (isHuman) {
            return true;
        } else {
            return false;
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

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
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
