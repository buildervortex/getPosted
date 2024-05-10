package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.Publication;
import com.getposted.model.PublicationDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "books", urlPatterns = "/books")
public class Books extends HttpServlet{

    private static Logger logger = Logging.getLogger(Books.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();
        List<Publication> publications = null;
        try {
            publications = publicationDAOImpl.getAll();
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get all publications. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        req.setAttribute("publications", publications);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/books.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        String filter = req.getParameter("filter");
        if(search == null) search = "";

        if((search.length() == 0) && filter == null){
            this.doGet(req, resp);
            return;
        }

        if(search.length() > 0){
            handleSearch(req,resp,search);
        }
        else if(filter != null){
            handleFilters(req,resp,filter);
        }
        return;
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response, String search) throws ServletException, IOException{
        PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();
        List<Publication> publications = null;
        try {
            publications = publicationDAOImpl.getAllPublicationsForGivenTitlePattern(search);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get title pattern publications. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        request.setAttribute("publications", publications);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/books.jsp");
        requestDispatcher.forward(request, response);
    }

    private void handleFilters(HttpServletRequest request, HttpServletResponse response, String filter) throws ServletException, IOException{
        PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();
        List<Publication> publications = null;
        try {
            publications = publicationDAOImpl.getAllPublicationsFilterBy(filter,true);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get filters in publications . The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        request.setAttribute("publications", publications);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/books.jsp");
        requestDispatcher.forward(request, response);        
    }
}
