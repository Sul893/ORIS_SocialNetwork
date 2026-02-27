package org.example.util;

public class Validator {
    public static boolean isValidTagname(String tagname) {
        if (tagname == null || tagname.isEmpty()) return false;
        return tagname.matches("^[a-zA-Z0-9_]{3,20}$");
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return password.length() >= 4;
    }

    public static boolean isValidPostContent(String content) {
        return content != null && content.length() > 0 && content.length() <= 280;
    }
}