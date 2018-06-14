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
public class Authorization {
    private int ID;
    private int userID;
    private int roleID;

    public Authorization() {
    }

    public Authorization(int ID, int userID, int roleID) {
        this.ID = ID;
        this.userID = userID;
        this.roleID = roleID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
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
    
    
}
