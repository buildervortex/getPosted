package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.Category;
import com.getposted.model.CategoryDAOImpl;
import com.getposted.model.Language;
import com.getposted.model.LanguageDAOImpl;
import com.getposted.model.Publication;
import com.getposted.model.PublicationDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="publicationUserView", urlPatterns = "/books/*")
public class PublicationUserView extends HttpServlet{

    private static Logger logger = Logging.getLogger(PublicationUserView.class.getName());
    private static String publicationUserViewJSPPath = "/WEB-INF/publicationUserView.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idAsString = req.getParameter("id");
        if(idAsString == null){
            resp.sendRedirect("/getPosted/book");
            return;
        }

        int id = Integer.parseInt(idAsString);
        PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();
        CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
        LanguageDAOImpl languageDAOImpl = new LanguageDAOImpl();

        Publication publication;
        Category category;
        Language language;

        try {
            publication = publicationDAOImpl.get(id);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the publication using id. The error message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        try {
            category = categoryDAOImpl.get(publication.getCategoryId());
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the publication using id. The error message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        try {
            language = languageDAOImpl.get(publication.getLanguageId());
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the publication using id. The error message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        req.setAttribute("publication", publication);
        req.setAttribute("category", category);
        req.setAttribute("language", language);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(publicationUserViewJSPPath);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}
