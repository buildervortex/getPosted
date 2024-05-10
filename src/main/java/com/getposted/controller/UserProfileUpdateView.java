package com.getposted.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.User;
import com.getposted.model.UserDAOImpl;
import com.getposted.random.RandomString;
import com.getposted.security.Passowrd;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@MultipartConfig
@WebServlet(name = "userProfileUpdateView", urlPatterns = "/user/update")
public class UserProfileUpdateView extends HttpServlet{

    private static final Logger logger = Logging.getLogger(UserProfileUpdateView.class.getName());
    private static final UserDAOImpl userDAOImpl = new UserDAOImpl();

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
            logger.warning("There is an exception happened when trying to get the user by id. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        };

        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/userProfileUpdateView.jsp");
        requestDispatcher.forward(req, resp);
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
        handleUserUpdate(req, resp, id);
    }

        private void handleUserUpdate(HttpServletRequest request, HttpServletResponse response, int id) throws IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String dob = request.getParameter("dob");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");

        if( ! checkForNullabilityOrEmphty(email,password,userName,dob,firstName,lastName)){
            response.sendRedirect("getPosted/user/update");
            return;
        }

        // hash the password
        String salt = RandomString.getRandomString(Passowrd.saltLength);
        String pepper = RandomString.getRandomString(Passowrd.pepperLength);
        String passwordHash = Passowrd.getHash(password,salt,pepper);


        UserDAOImpl userDAOImpl = new UserDAOImpl();
        // creat the user 
        User user = new User(id,email,passwordHash,userName,Date.valueOf(dob),salt,pepper,firstName,middleName,lastName);

        try {
            userDAOImpl.update(user);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to update the user. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        };
        response.sendRedirect("/getPosted/user");
    }
    private boolean checkForNullabilityOrEmphty(String... vars){
        boolean isValid = true;

        for(String i: vars){
            if(i.length() == 0 || i == null)
            {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
    
}
