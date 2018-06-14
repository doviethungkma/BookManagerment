package struts.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import struts.model.User;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lanth
 */
public class UserDAO {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public String hash(String password, String SALT) {
        try {
            String textHash = password + SALT;
            MessageDigest msDigest = MessageDigest.getInstance("SHA-256");
            msDigest.update(textHash.getBytes());
            byte[] result = msDigest.digest();
            return new UserDAO().bytesToHex(result);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

//    public static void main(String[] args) {
//        UserDAO udao = new UserDAO();
//        String s = udao.hash("123456aA@", "921B1942E7E912783C42EB68C035BC1EF475B858EACF988CDFE902CE577C9028");
//        System.out.println(s);
//    }

    public int checkLogin(String username, String password) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM tbl_user where Username = ?";
        int pwdID = 0;
        int loginID = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                pwdID = rs.getInt("PwdID");
                loginID = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql2 = "SELECT * FROM tbl_password WHERE ID = ?";
        try {
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, pwdID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String passHash = rs.getString("Password");
                String SALT = rs.getString("SALT");
                if (passHash.equals(new UserDAO().hash(password, SALT))) {
                    return loginID;
                } else {
                    return 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 0;
    }

    public List<User> getAllUser() {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> lstAllUser = new ArrayList<>();
        String sql = "Select * from tbl_user";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setID(rs.getInt("ID"));
                user.setUsername(rs.getString("Username"));
                user.setFullname(rs.getString("Fullname"));
                user.setEmail(rs.getString("Email"));
                user.setAvatar(rs.getString("Avatar"));
                user.setPwdID(rs.getInt("PwdID"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                lstAllUser.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstAllUser;
    }

    public boolean checkAdmin(int ID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        DataAccess da = new DataAccess();
        conn = da.getConnection();
        String sql = "Select * from tbl_user where ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean("isAdmin")) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public User getUserByID(int userID) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM tbl_user WHERE ID = ?";
        User user = new User();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setID(rs.getInt("ID"));
                user.setUsername(rs.getString("Username"));
                user.setFullname(rs.getString("Fullname"));
                user.setEmail(rs.getString("Email"));
                user.setAvatar(rs.getString("Avatar"));
                user.setPwdID(rs.getInt("PwdID"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public boolean deleteUser(int ID) {
        DataAccess da = new DataAccess();
        Connection conn = conn = da.getConnection();
        PreparedStatement ps;

        //Xóa các comment của user trong bảng comment
        try {
            String sql = "Delete from tbl_comment where UserID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);

            String sql1 = "Delete from tbl_user where ID = ?";
            try {
                ps = conn.prepareStatement(sql1);
                ps.setInt(1, ID);
                if (ps.executeUpdate() > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean addUser(String username, String password, String fullname, String email, String avatar, int isAdmin) {
        try {
            DataAccess da = new DataAccess();
            Connection conn = da.getConnection();
            conn.setAutoCommit(false);
            UserDAO userDAO = new UserDAO();
            PreparedStatement ps;
            ResultSet rs;

            String sql = "INSERT INTO tbl_password (Password, DateCreated, Salt) "
                    + "VALUE (?, CURRENT_TIME, ?)";

            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(32);
            String SALT = userDAO.bytesToHex(salt);
            ps.setString(1, userDAO.hash(password, SALT));
            ps.setString(2, SALT);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            rs.next();
            int pwdID = rs.getInt(1);

            String sql2 = "INSERT INTO tbl_user (Username, PwdID, Fullname, Email, Avatar, isAdmin)"
                    + " VALUE (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql2);
            ps.setString(1, username);
            ps.setInt(2, pwdID);
            ps.setString(3, fullname);
            ps.setString(4, email);
            ps.setString(5, avatar);
            ps.setInt(6, isAdmin);
            ps.executeUpdate();
            conn.commit();
            conn.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean updateUser(int ID, String fullname, String email, String avatar, int isAdmin) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        String sql = "Update tbl_user "
                + "set Fullname = ?, Email = ?, Avatar = ?, isAdmin = ? "
                + "where ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, avatar);
            ps.setInt(4, isAdmin);
            ps.setInt(5, ID);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean changePassword(String email, String password) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT * FROM tbl_user WHERE Email = ?";
        int pwdID = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                pwdID = rs.getInt("PwdID");
            }

            String sql2 = "UPDATE tbl_password SET Password = ?, SALT = ?, DateCreated = CURRENT_TIME WHERE ID  = ?";
            ps = conn.prepareStatement(sql2);
            UserDAO userDAO = new UserDAO();
            byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(32);
            String SALT = userDAO.bytesToHex(salt);

            ps.setString(1, userDAO.hash(password, SALT));
            ps.setString(2, SALT);
            ps.setInt(3, pwdID);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean editUserProfile(int ID, String fullname, String email, String avatar) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        String sql = "Update tbl_user "
                + "set Fullname = ?, Email = ?, Avatar = ? "
                + "where ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, avatar);
            ps.setInt(4, ID);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isExistEmail(String email) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String> lstMail = new ArrayList<>();

        String sql = "SELECT email FROM tbl_user";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lstMail.add(rs.getString("Email"));
            }

            for (String DBMail : lstMail) {
                if (DBMail.equals(email)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.equals("")) {
            return false;
        }
        return email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
    }

    /*
    *   Check User
     */
    public boolean isExistUsername(String username) {
        DataAccess da = new DataAccess();
        Connection conn = da.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String> lstUser = new ArrayList<>();

        String sql = "SELECT Username FROM tbl_user";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lstUser.add(rs.getString("Username"));
            }

            for (String DBUser : lstUser) {
                if (DBUser.equals(username)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isValidUsername(String username) {
        if (username == null || username.equals("")) {
            return false;
        }
        return username.matches("^[a-zA-Z0-9]{6,30}$");
    }

    /*
    *   Check Password
     */
    public boolean isStrongPassword(String password) {
        if (password == null) {
            return false;
        }

        return password.matches("^((?=(.*[a-zAZ]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s)).{8,}$");
    }

//    private boolean isBlackListPassword(String password) {
//        BufferedReader br = null;
////        br = new BufferedReader(new InputStreamReader());
//        return false;
//    }
//    private static boolean isPassUserInfo(User userinfo, String password) {
//        if (password.toLowerCase().contains(userinfo.getUsername().toLowerCase())) {
//            return true;
//        }
//        if (password.toLowerCase().contains(userinfo.getEmail().toLowerCase())) {
//            return true;
//        }
//        if (password.toLowerCase().contains(userinfo.getFullname().toLowerCase())) {
//            return true;
//        }
//        return false;
//    }
//    public public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//        userDAO.addUser("Admin1", "123456", "Doviethung", "shadyside1995@gmail.com", "a.jpg", 1);
//    }
}
