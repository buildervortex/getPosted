package com.getposted.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.enums.Stored;
import com.getposted.fileHandler.FileManager;
import com.getposted.logger.Logging;
import com.getposted.model.Author;
import com.getposted.model.AuthorDAOImpl;
import com.getposted.model.Country;
import com.getposted.model.CountryDAOImpl;
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
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "authorProfileUpdateView", urlPatterns="/author/update")
public class AuthorProfileUpdateView extends HttpServlet{

    private static final AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
    private static final Logger logger = Logging.getLogger(AuthorProfileUpdateView.class.getName());
    private static final CountryDAOImpl countryDAOImpl = new CountryDAOImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(! SessionManagement.isLoggedIn(session)){
            resp.sendRedirect("/getPosted/login");
            return;
        }
        String type = (String) session.getAttribute("type");
        int id = (int) session.getAttribute("id");

        if(!type.equals("author")){
            resp.sendRedirect("/getPosted/login");
            return;
        }

        Author author;
        List<Country> countries;

        try {
            author = authorDAOImpl.get(id);
        } catch (SQLException e) {
            logger.warning("The exception occoured when trying to get the author by id. the exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        try {
            countries = countryDAOImpl.getAll();
        } catch (SQLException e) {
            logger.warning("The exception occoured when trying to get the countries by getall. the exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        req.setAttribute("author", author);
        req.setAttribute("countries", countries);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/authorProfileUpdateView.jsp");
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

        if(!type.equals("author")){
            resp.sendRedirect("/getPosted/login");
            return;
        }
        Author author;

        try {
            author = authorDAOImpl.get(id);
        } catch (SQLException e) {
            logger.warning("The exception occoured when trying to get the author by id. the exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        authorUpdateHandler(req, resp, id);
        fileUploadHandler(req,resp,"file",author);
        return;
    }
    
    private long fileUploadHandler(HttpServletRequest request, HttpServletResponse response, String fileName, Author author) throws IOException, ServletException{
        long readSize = 0;
        Part filePart = request.getPart(fileName);
        if(filePart == null || filePart.getSize() == 0)return readSize;

        String storingFileName = author.getId()+".png";

        InputStream inputStream = filePart.getInputStream();
        readSize = FileManager.storeFile(storingFileName,inputStream,Stored.AUTHORPROFILEPICTURE);
        return readSize;
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
    private void authorUpdateHandler(HttpServletRequest request, HttpServletResponse response, int id) throws IOException{
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
            response.sendRedirect("/getPosted/author/update");
            return;
        }

        // hash the password
        String salt = RandomString.getRandomString(Passowrd.saltLength);
        String pepper = RandomString.getRandomString(Passowrd.pepperLength);
        String passwordHash = Passowrd.getHash(password,salt,pepper);

        AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
        
        // create the Author
        Author author = new Author(id,email,phoneNumber,salt,bio,pepper,passwordHash,Date.valueOf(dob),firstName,middleName,lastName,userName,countryId);

        try {
            authorDAOImpl.update(author);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to update the author. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        response.sendRedirect("/getPosted/author");

    }
}
