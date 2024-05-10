package com.getposted.controller;

import java.io.IOException;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionManagement {

    private static Logger logger = Logging.getLogger(Login.class.getName());
    // TODO: Add the redirection to the user profile user view
    private static String userProfileUserView = "/getPosted";
    // TODO: Add the redirectiont othe author profile author view
    private static String authorProfileAuthorView = "/getPosted";

    public static boolean ifLoggedInRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean loggedIn = false;

        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("type") != null) {
            loggedIn = true;
            String type = (String) session.getAttribute("type");
            if (type.equals("user")) {
                response.sendRedirect(userProfileUserView);
            } else if (type.equals("author")) {
                response.sendRedirect(authorProfileAuthorView);
            }
        }
        return loggedIn;
    }

    public static boolean isLoggedIn(HttpSession session){
        boolean loggedIn = false;

        if (session != null && session.getAttribute("type") != null) {
            loggedIn = true;
        }

        return loggedIn;
    }

}
