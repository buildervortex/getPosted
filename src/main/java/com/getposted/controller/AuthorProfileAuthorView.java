package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.getposted.enums.UrlPatterns;
import com.getposted.logger.Logging;
import com.getposted.model.Author;
import com.getposted.model.AuthorDAOImpl;
import com.getposted.model.Country;
import com.getposted.model.CountryDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="authorProfileAuthorView",urlPatterns = UrlPatterns.author)
public class AuthorProfileAuthorView extends HttpServlet{

    private static final AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
    private static final Logger logger = Logging.getLogger(AuthorProfileAuthorView.class.getName());
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
        Country country;

        try {
            author = authorDAOImpl.get(id);
        } catch (SQLException e) {
            logger.warning("The exception occoured when trying to get the author by id. the exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        try {
            country = countryDAOImpl.get(author.getCountryId());
        } catch (SQLException e) {
            logger.warning("The exception occoured when trying to get the country by id. the exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        req.setAttribute("author", author);
        req.setAttribute("country", country);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/authorProfileAuthorView.jsp");
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
    }
}
