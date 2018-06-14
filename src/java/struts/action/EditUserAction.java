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
 * @author shadyside
 */
public class EditUserAction {

    private int ID;
    private String username;
    private String fullname;
    private String email;
    private String role;
    private List lstUser;
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;
    private String error;
    private String mailError;

    public EditUserAction() {
    }

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        Map session = ActionContext.getContext().getSession();
        FileManager fileManager = new FileManager();
        int loginID = (int) session.get("loginID");
        if (loginID == 0) {
            return "login";
        } else if (userDAO.checkAdmin(loginID) == false) {
            return "login";
        } else {
            int isAdmin = 0;
            if (role.equals("Admin")) {
                isAdmin = 1;
            }

            //Kiem tra truong email
            if (userDAO.isValidEmail(email) == false) {
                mailError = "Sai định dạng email";
                return "input";
            }

            String imageName = userDAO.getUserByID(ID).getAvatar();
            if (userImageFileName == null) {
                userImageFileName = imageName;
            } else if (fileManager.safeUploadFile(userImageFileName, userImageContentType)) {
                //Xử lý file upload
                String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("userimages");
                File fileToCreate = new File(filePath, userImageFileName);
                FileUtils.copyFile(userImage, fileToCreate);
            } else {
                return "fail";
            }

            boolean result = userDAO.updateUser(ID, fullname, email, userImageFileName, isAdmin);
            if (result) {
                lstUser = userDAO.getAllUser();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}
