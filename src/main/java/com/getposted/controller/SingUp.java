package com.getposted.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


import com.getposted.logger.Logging;
import com.getposted.model.Author;
import com.getposted.model.AuthorDAOImpl;
import com.getposted.model.CountryDAOImpl;
import com.getposted.model.User;
import com.getposted.model.UserDAOImpl;
import com.getposted.random.RandomString;
import com.getposted.security.Passowrd;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="singUp", urlPatterns = "/singup/*")
public class SingUp extends HttpServlet{

    private static Logger logger = Logging.getLogger(SingUp.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(SessionManagement.ifLoggedInRedirect(req, resp)) return;

        RequestDispatcher userSingUpDispatcher = req.getRequestDispatcher("/WEB-INF/userSingUp.jsp");
        RequestDispatcher authorSingUpDispatcher = req.getRequestDispatcher("/WEB-INF/authorSingUp.jsp");
        String type = req.getParameter("type");

        if(type == null){
            ServeErrorPage.giveError(req, resp);
            return;
        }

        if(type.equals("user")){
            userSingUpDispatcher.forward(req, resp);
        }
        else if(type.equals("author")){
            CountryDAOImpl countryDAOImpl = new CountryDAOImpl();
            try {
                req.setAttribute("countries", countryDAOImpl.getAll());
            } catch (SQLException e) {
                logger.warning("There is an exception occoured when trying to get hte countries using getAll method of CountryDAOImpl class. the error message is "+e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
            authorSingUpDispatcher.forward(req, resp);
        }
        else{
            ServeErrorPage.giveError(req, resp);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        if(SessionManagement.ifLoggedInRedirect(req, resp)) return;

        String type = req.getParameter("type");
        if(type == null){
            ServeErrorPage.giveError(req, resp);
            return;
        }
        if(type.equals("author")){ handleAuthorSingUp(req, resp); }
        else if(type.equals("user")) { handleUserSingUp(req, resp); }
        else{
            ServeErrorPage.giveError(req, resp);
            return;
        }
    }

    private void handleAuthorSingUp(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String bio = request.getParameter("bio");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        int countryId = Integer.parseInt(request.getParameter("countryId"));

        if( ! checkForNullabilityOrEmphty(email,phoneNumber,password,dob,firstName,lastName,userName)){
            response.sendRedirect(String.format("/getPosted/singup?type=author&email=%s&phoneNumber=%s&dob=%s&firstName=%s&middleName=%s&lastName=%s&userName=%s",email,phoneNumber,dob,firstName,middleName,lastName,userName));
            return;
        }

        if(checkIfTheAuthorEmailAvailable(email)){
            response.sendRedirect("/getPosted/login");
            return;
        }

        // hash the password
        String salt = RandomString.getRandomString(Passowrd.saltLength);
        String pepper = RandomString.getRandomString(Passowrd.pepperLength);
        String passwordHash = Passowrd.getHash(password,salt,pepper);

        AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
        // create the Author
        Author author = new Author(0,email,phoneNumber,salt,bio,pepper,passwordHash,Date.valueOf(dob),firstName,middleName,lastName,userName,countryId);

        try {
            authorDAOImpl.insert(author);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to save the author. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        // TODO
        // add code to redirect to the author profile author view
        response.sendRedirect("/getPosted/");

    }
    private void handleUserSingUp(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String dob = request.getParameter("dob");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");

        if( ! checkForNullabilityOrEmphty(email,password,userName,dob,firstName,lastName)){
            response.sendRedirect(String.format("/getPosted/singup?type=user&email=%s&userName=%s&dob=%s&firstName=%s&middleName=%s&lastName=%s",email,userName,dob,firstName,middleName,lastName));
            return;
        }

        if(checkIfTheUserEmailAvailable(email)){
            response.sendRedirect("/getPosted/login");
            return;
        }

        // hash the password
        String salt = RandomString.getRandomString(Passowrd.saltLength);
        String pepper = RandomString.getRandomString(Passowrd.pepperLength);
        String passwordHash = Passowrd.getHash(password,salt,pepper);


        UserDAOImpl userDAOImpl = new UserDAOImpl();
        // creat the user 
        User user = new User(0,email,passwordHash,userName,Date.valueOf(dob),salt,pepper,firstName,middleName,lastName);

        try {
            userDAOImpl.insert(user);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to save the user. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        };

        // TODO
        // add the code to redirect users to the user profile user view
        response.sendRedirect("/getPosted/");
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

    private boolean checkIfTheAuthorEmailAvailable(String email) {
        boolean available = true;
        AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
        List<Author> authors;

        try {
            authors = authorDAOImpl.getAllAuthorsByEmail(email);
        } catch (SQLException e) {
            logger.warning("The SQLException occoured when trying to check for the author availability using his email. the error message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        if(authors.size() == 0){
            return false;
        }

        return available;
    }

    private boolean checkIfTheUserEmailAvailable(String email) {
        boolean available = true;
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        List<User> users;

        try {
            users = userDAOImpl.getAllUsersByGivenEmailPattern(email);
        } catch (SQLException e) {
            logger.warning("The SQLException occoured when trying to check for the author availability using his email. the error message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        if(users.size() == 0){
            return false;
        }

        return available;
    }

}
