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
 * @author vm-os05
 */
public class ManageUserAction {

    private List lstUser;

    public ManageUserAction() {
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
            lstUser = userDAO.getAllUser();
            return "success";
        }
    }

    public List getLstUser() {
        return lstUser;
    }

    public void setLstUser(List lstUser) {
        this.lstUser = lstUser;
    }

}
