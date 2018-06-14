/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.model;

/**
 *
 * @author shadyside
 */
public class Comment {

    private int ID;
    private int userID;
    private int bookID;
    private String content;
    private String dateCreated;
    private String username;
    private String userImage;

    public Comment() {
    }

    public Comment(int ID, int userID, int bookID, String content, String dateCreated, String username, String userImage) {
        this.ID = ID;
        this.userID = userID;
        this.bookID = bookID;
        this.content = content;
        this.dateCreated = dateCreated;
        this.username = username;
        this.userImage = userImage;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

}
