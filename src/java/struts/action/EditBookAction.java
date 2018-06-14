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

/**
 *
 * @author shadyside
 */
public class EditBookAction {

    private int ID;
    private String title;
    private String author;
    private double price;
    private String description;
    private Book book;
    private File bookImage;
    private String bookImageContentType;
    private String bookImageFileName;
    private List<Book> lstBook;

    public EditBookAction() {
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
            BookDAO bookDAO = new BookDAO();

            String imageName = bookDAO.getBookByID(ID).getImage();
            if (bookImageFileName == null) {
                bookImageFileName = imageName;
            } else if (fileManager.safeUploadFile(bookImageFileName, bookImageContentType)) {
                String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("bookimages");
                System.out.println("Image Location:" + filePath);//see the server console for actual location  
                File fileToCreate = new File(filePath, bookImageFileName);
                FileUtils.copyFile(bookImage, fileToCreate);//copying source file to new file
            } else {
                return "fail";
            }

            boolean result = bookDAO.updateBook(ID, title, author, price, description, bookImageFileName);
            lstBook = bookDAO.getAllBook();

            if (result) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public File getBookImage() {
        return bookImage;
    }

    public void setBookImage(File bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookImageContentType() {
        return bookImageContentType;
    }

    public void setBookImageContentType(String bookImageContentType) {
        this.bookImageContentType = bookImageContentType;
    }

    public String getBookImageFileName() {
        return bookImageFileName;
    }

    public void setBookImageFileName(String bookImageFileName) {
        this.bookImageFileName = bookImageFileName;
    }

    public List<Book> getLstBook() {
        return lstBook;
    }

    public void setLstBook(List<Book> lstBook) {
        this.lstBook = lstBook;
    }

}
