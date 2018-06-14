/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lanth
 */
public class DataAccess {

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf-8", "root", "");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf-8", "root", "");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        DataAccess da = new DataAccess();
        System.out.println(da.getConnection());
    }

}
