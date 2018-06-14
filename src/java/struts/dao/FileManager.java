/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts.dao;

import java.util.Arrays;

/**
 *
 * @author shadyside
 */
public class FileManager {

    private static String getSafeFileName(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != '/' && c != '\\' && c != 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static final String[] allowedMimetype = new String[]{"image/jpeg",
        "image/png", "image/gif"};

    public boolean safeUploadFile(String fileName, String fileContentType) {

        //Loại bỏ ký tự / \, null trong tên file
        fileName = getSafeFileName(fileName);

        //Kiểm tra phần mở rộng
        if (!(fileName.endsWith(".jpg")) && 
                !(fileName.endsWith(".jpeg")) && !(fileName.endsWith(".png"))) {
            return false;
        }

        boolean mimeTypeAccepted
                = Arrays.asList(allowedMimetype).contains(fileContentType);
        if (!mimeTypeAccepted) {
            return false;
        }
        
        return true;
    }
}
