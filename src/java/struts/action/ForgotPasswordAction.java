/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.action;

import org.apache.commons.lang3.RandomStringUtils;
import struts.dao.UserDAO;
import struts.model.SendMail;

/**
 *
 * @author shadyside
 */
public class ForgotPasswordAction {

    private String email;

    public ForgotPasswordAction() {
    }

    public String execute() throws Exception {
        String newPass = generateSecurePassword();
        UserDAO userDAO = new UserDAO();
        if (userDAO.isExistEmail(email)) {
            boolean result = userDAO.changePassword(email, newPass);
            if (result) {
                SendMail sendMail = new SendMail();
                sendMail.sendMail(email, "Thong bao thay doi mat khau", "Mat khau cua ban da duoc chuyen thanh " + newPass + ". Hay dang nhap voi mat khau moi");
                return "success";
            } else {
                return "fail";
            }
        } else {
            return "fail";
        }
    }

//    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrsruvwxyz!@#$%^&*";
//    static Random rnd = new Random();
//
//    public String randomString(int len) {
//        StringBuilder sb = new StringBuilder(len);
//        for (int i = 0; i < len; i++) {
//            sb.append(AB.charAt(rnd.nextInt(AB.length())));
//        }
//        return sb.toString();
//    }
    public static String generateSecurePassword() {
        String randomString = RandomStringUtils.randomAlphabetic(4) + RandomStringUtils.random(4, "~!@#$%^&*()") + RandomStringUtils.randomNumeric(4);
        String securePassword = RandomStringUtils.random(12, randomString);
        return securePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
