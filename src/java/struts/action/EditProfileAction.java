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
import struts.dao.BookDAO;
import struts.dao.FileManager;
import struts.dao.UserDAO;
import struts.model.Book;
import struts.model.User;

/**
 *
 * @author shadyside
 */
public class EditProfileAction {

    private int ID;
    private String fullname;
    private String email;
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;
    private List<Book> lstBook;
    private String error;

    public EditProfileAction() {
    }

    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        BookDAO bookDAO = new BookDAO();
        FileManager fileManager = new FileManager();
        Map session = ActionContext.getContext().getSession();
        User user = (User) session.get("USER");
        if (user == null) {
            return "login";
        }/* else if (userDAO.checkAdmin(user.getUsername()) == true) {
            return "login";
        }*/ else {
            ID = (int) session.get("loginID");

            //Kiem tra truong email
            if (userDAO.isValidEmail(email) == false) {
                error = "Sai định dạng email";
                return "input";
            }

            String imageName = userDAO.getUserByID(ID).getAvatar();
            if (userImageFileName == null) {
                userImageFileName = imageName;
            } else if (fileManager.safeUploadFile(userImageFileName, userImageContentType)) {
                //Xử lý file upload
//                String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("userimages");
                String filePath = "D:\\HungDV\\BookManager vFinal\\BookManagement_NEW_VER1.0\\web\\upload";
//                System.out.println(userImageContentType);
                File fileToCreate = new File(filePath, userImageFileName);
                FileUtils.copyFile(userImage, fileToCreate);//copying source file to new file  
            } else {
                return "fail";
            }

            boolean result = userDAO.editUserProfile(ID, fullname, email, userImageFileName);
            if (result) {
                lstBook = bookDAO.getAllBook();
                user = userDAO.getUserByID(ID);
                session.put("USER", user);
                return "success";
            } else {
                return "fail";
            }
        }
    }

    public EditProfileAction(int ID, String fullname, String email, File userImage, String userImageContentType, String userImageFileName, List<Book> lstBook) {
        this.ID = ID;
        this.fullname = fullname;
        this.email = email;
        this.userImage = userImage;
        this.userImageContentType = userImageContentType;
        this.userImageFileName = userImageFileName;
        this.lstBook = lstBook;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public List<Book> getLstBook() {
        return lstBook;
    }

    public void setLstBook(List<Book> lstBook) {
        this.lstBook = lstBook;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
