/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.action;

import com.opensymphony.xwork2.ActionContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import struts.dao.UserDAO;
import struts.model.User;

/**
 *
 * @author shadyside
 */
public class BackUpAction {

    private String fileName;
    private BufferedReader bufferedReader;
    private String result;

    public BackUpAction() {
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
            //fileName = fileName.replaceAll("[^a-z0-9\\-\\.]", "");
            Process p = Runtime.getRuntime().exec("cmd /c C://\"Program Files\"/WinRAR/rar.exe a -m5 D://" + fileName + " D:\\image");
            bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                result += line + "\r\n";
            }
            return "success";
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
