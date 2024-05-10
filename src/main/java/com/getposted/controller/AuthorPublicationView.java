package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.Author;
import com.getposted.model.AuthorDAOImpl;
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
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "authorPublicationView", urlPatterns = "/author/books/*")
public class AuthorPublicationView extends HttpServlet{

    private static final Logger logger = Logging.getLogger(AuthorPublicationView.class.getName());
    private static final PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();
    private static final CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
    private static final LanguageDAOImpl languageDAOImpl = new LanguageDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(! SessionManagement.isLoggedIn(session)){
            resp.sendRedirect("/getPosted/login");
            return;
        }
        String type = (String) session.getAttribute("type");
        int authorId = (int) session.getAttribute("id");

        if(!type.equals("author")){
            resp.sendRedirect("/getPosted/login");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));

        Publication publication;
        Category category;
        Language language;

        try {
            publication = publicationDAOImpl.get(id);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the publication using id. The exception message is "+e.getMessage());
            throw new RuntimeException();
        }
        try {
            category = categoryDAOImpl.get(publication.getCategoryId());
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the category using id. The exception message is "+e.getMessage());
            throw new RuntimeException();
        }
        try {
            language = languageDAOImpl.get(publication.getLanguageId());
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the language using id. The exception message is "+e.getMessage());
            throw new RuntimeException();
        }
        

        req.setAttribute("publication", publication);
        req.setAttribute("category", category);
        req.setAttribute("language", language);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/authorPublicationView.jsp");
        dispatcher.forward(req, resp);
    }
    
}
