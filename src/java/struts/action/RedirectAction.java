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
 * @author shadyside
 */
public class RedirectAction {

    private List<User> lstAllUser;

    public RedirectAction() {
    }

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        Map session = ActionContext.getContext().getSession();
        if ((int) session.get("loginID") == 0) {
            return "login";
        } else {
            lstAllUser = userDAO.getAllUser();
            return "success";
        }
    }

    public List<User> getLstAllUser() {
        return lstAllUser;
    }

    public void setLstAllUser(List<User> lstAllUser) {
        this.lstAllUser = lstAllUser;
    }

}
