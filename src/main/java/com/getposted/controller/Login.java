package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.Author;
import com.getposted.model.AuthorDAOImpl;
import com.getposted.model.User;
import com.getposted.model.UserDAOImpl;
import com.getposted.security.Passowrd;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="loging",urlPatterns = "/login")
public class Login extends HttpServlet{

    private static Logger logger = Logging.getLogger(Login.class.getName());
    // TODO: Add the redirection to the user profile user view
    private static String userProfileUserView = "/getPosted";
    // TODO: Add the redirectiont othe author profile author view
    private static String authorProfileAuthorView = "/getPosted";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check whether the user or author logged in
        if(SessionManagement.ifLoggedInRedirect(req, resp)) return;

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(SessionManagement.ifLoggedInRedirect(req, resp)) return;

        if(req.getParameter("type") == null){
            handleUserLogin(req, resp);
            return;
        }
        else{
            handleAuthorLogin(req, resp);
            return;
        }
    }

    private void handleUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(!checkAvailability(email,password)){
            resp.sendRedirect("/getPosted/login");
            return;
        }

        if(! checkUserLogin(email, password, req)){
            //TODO add the code to handle wrong credentials
            return;
        }

        resp.sendRedirect(userProfileUserView);
        return;


    }
    private void handleAuthorLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(!checkAvailability(email,password)){
            resp.sendRedirect("/getPosted/login");
            return;
        }
        
        if(! checkAuthorLogin(email, password , req)){
            //TODO add the code to handle wrong credentials
            return;
        }
        
        resp.sendRedirect(authorProfileAuthorView);
        return;
    }

    private boolean checkAvailability(String... parameters){
        boolean available = true;

        for(String i: parameters){
            if(i == null || i.length() == 0) return false;
        }

        return available;
    }

    private boolean checkAuthorLogin(String email, String password, HttpServletRequest request){
        boolean success = true;
        AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
        Author author= null;
        List<Author> authors = null;

        try {
            authors = authorDAOImpl.getListOfAuthorsByEmail(email, 1);
        } catch (SQLException e) {
            logger.warning("There is an SQLException happend whe trying to get list of authors by email. The error message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        if(authors == null || authors.size() == 0){
            return false;
        }

        author = authors.get(0);

        // get password hash
        String passwordHash = Passowrd.getHash(password,author.getSalt(),author.getPepper());
        String actualPasswordHash = author.getPassword();

        if(! actualPasswordHash.equals(passwordHash)) return false;

        HttpSession session = request.getSession();
        session.setAttribute("type", "author");
        session.setAttribute("id", author.getId());
        session.setMaxInactiveInterval(3600);

        return success;
    }

    private boolean checkUserLogin(String email, String password, HttpServletRequest request){
        boolean success = true;
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        User user= null;
        List<User> users = null;

        try {
            users = userDAOImpl.getAllUsersByGivenEmailPattern(email);
        } catch (SQLException e) {
            logger.warning("There is an SQLException happend whe trying to get list of users by email. The error message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        if(users == null || users.size() == 0){
            return false;
        }

        user = users.get(0);

        // get password hash
        String passwordHash = Passowrd.getHash(password,user.getSalt(),user.getPepper());
        String actualPasswordHash = user.getPassword();

        if(! actualPasswordHash.equals(passwordHash)) return false;

        HttpSession session = request.getSession();
        session.setAttribute("type", "user");
        session.setAttribute("id", user.getId());
        session.setMaxInactiveInterval(3600);

        return success;
    }
}
