package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.User;
import com.getposted.model.UserDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "userProfileUserView", urlPatterns = "/user")
public class UserProfileUserView extends HttpServlet{

    private static final UserDAOImpl userDAOImpl = new UserDAOImpl();
    private static final Logger logger = Logging.getLogger(UserProfileUserView.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(! SessionManagement.isLoggedIn(session)){
            resp.sendRedirect("/getPosted/login");
            return;
        }
        String type = (String) session.getAttribute("type");
        int id = (int) session.getAttribute("id");

        if(!type.equals("user")){
            resp.sendRedirect("/getPosted/login");
            return;
        }

        User user;

        try {
            user = userDAOImpl.get(id);
        } catch (SQLException e) {
            logger.warning("The exception occoured when trying to get the user by id. the exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        };

        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/userProfileUserView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(! SessionManagement.isLoggedIn(session)){
            resp.sendRedirect("/getPosted/login");
            return;
        }
        String type = (String) session.getAttribute("type");
        int id = (int) session.getAttribute("id");

        if(!type.equals("user")){
            resp.sendRedirect("/getPosted/login");
            return;
        }        
    }
    
}
