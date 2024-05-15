package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.enums.UrlPatterns;
import com.getposted.logger.Logging;
import com.getposted.model.Author;
import com.getposted.model.AuthorDAOImpl;
import com.getposted.model.Country;
import com.getposted.model.CountryDAOImpl;
import com.getposted.model.Publication;
import com.getposted.model.PublicationDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "authors",urlPatterns = UrlPatterns.authors)
public class Authors extends HttpServlet{

    private static Logger logger = Logging.getLogger(Authors.class.getName());
    private static AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
    private static CountryDAOImpl countryDAOImpl = new CountryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = null;

        try {
            authors = authorDAOImpl.getAll();
        } catch (SQLException e) {
            logger.warning("There is an exception occcoured in when trying to get all authors. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        for(Author author: authors){
            if(author.getMiddleName() == null) author.setMiddleName("");
        }

        req.setAttribute("countryDAOImplObj", countryDAOImpl);
        req.setAttribute("authors", authors);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/authors.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        if(search == null) search = "";

        if((search.length() == 0)){
            this.doGet(req, resp);
            return;
        }

        if(search.length() > 0){
            handleSearch(req,resp,search);
        }
        return;
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response, String search) throws ServletException, IOException{
        List<Author> authors = null;
        try {
            authors = authorDAOImpl.getAllAuthorsByName(search);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get name pattern authors. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        request.setAttribute("authors", authors);
        request.setAttribute("countryDAOImplObj", countryDAOImpl);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/authors.jsp");
        requestDispatcher.forward(request, response);
    }
    
    
}
