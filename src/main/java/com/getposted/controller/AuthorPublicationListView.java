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
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "authorPublicationListView", urlPatterns = "/author/books")
public class AuthorPublicationListView extends HttpServlet{

    private static final Logger logger = Logging.getLogger(AuthorPublicationListView.class.getName());
    private static final PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();

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
        List<Publication> publications;

        try {
            publications = publicationDAOImpl.getAllPublicationsBelongsToAnAuthor(id);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get all publications for an author. the exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        req.setAttribute("publications",publications);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/authorPublicationListView.jsp");
        dispatcher.forward(req, resp);
    }
    
}
