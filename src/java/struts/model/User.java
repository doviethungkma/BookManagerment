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
public class User {

    private int ID;
    private String username;
    private String fullname;
    private String avatar;
    private String email;
    private int pwdID;
    private boolean isAdmin;

    public User() {
    }

    public User(int ID, String username, String fullname, String avatar, String email, int pwdID) {
        this.ID = ID;
        this.username = username;
        this.fullname = fullname;
        this.avatar = avatar;
        this.email = email;
        this.pwdID = pwdID;
    }

    public int getPwdID() {
        return pwdID;
    }

    public void setPwdID(int pwdID) {
        this.pwdID = pwdID;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
