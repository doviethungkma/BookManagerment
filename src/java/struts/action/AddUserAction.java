/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.action;

import com.opensymphony.xwork2.ActionContext;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import struts.dao.FileManager;
import struts.dao.UserDAO;

/**
 *
 * @author vm-os05
 */
public class AddUserAction {

    private String username;
    private String password;
    private String fullname;
    private String email;
    private String role;
    private List lstUser;
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;
    private String error;
    private String mailError;
    private String passwordError;

    public AddUserAction() {
    }

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        FileManager fileManager = new FileManager();
        Map session = ActionContext.getContext().getSession();
        int loginID = (int) session.get("loginID");

        if (loginID == 0) {
            return "login";
        } else if (userDAO.checkAdmin(loginID) == false) {
            return "login";
        } else if (validate()) {
            int isAdmin = 0;
            if (role.equals("Admin")) {
                isAdmin = 1;
            }

            /*Kiểm tra file nhập vào có hợp lệ không*/
            if (fileManager.safeUploadFile(userImageFileName, userImageContentType)) {
                //Xử lý file upload
//                String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("userimages");
                String filePath = "/upload";
                File fileToCreate = new File(filePath, userImageFileName);
                FileUtils.copyFile(userImage, fileToCreate);
            } else {
                return "fail";
            }

            boolean result = userDAO.addUser(username, password, fullname, email, userImageFileName, isAdmin);
            lstUser = userDAO.getAllUser();

            if (result) {
                return "success";
            } else {
                return "fail";
            }
        } else {
            return "input";
        }
    }

    public boolean validate() {
        UserDAO userDAO = new UserDAO();
        if (userDAO.isExistUsername(username)) {
            error = "Username đã tồn tại";
            return false;
        } else if (username.length() < 6) {
            error = "Username tối thiểu là 6 ký tự";
            return false;
        } else if (!userDAO.isValidUsername(username)) {
            error = "Username cần chứa chữ hoa, chữ thường, số ký tự đặc biệt";
            return false;
        }

        //Kiem tra truong email
        if (userDAO.isValidEmail(email) == false) {
            mailError = "Sai định dạng email";
            return false;
        }

        if (userDAO.isStrongPassword(password) == false) {
            passwordError = "Password cần tối thiểu 6 ký tự và bao gồm chữ hoa, thường, số, ký tự";
            return false;
        }
        return true;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List getLstUser() {
        return lstUser;
    }

    public void setLstUser(List lstUser) {
        this.lstUser = lstUser;
    }

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMailError() {
        return mailError;
    }

    public void setMailError(String mailError) {
        this.mailError = mailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

}
