package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.getposted.enums.Stored;
import com.getposted.fileHandler.FileManager;
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

@WebServlet(name = "authorPublicationDeleteView", urlPatterns = "/author/books/delete/*")
public class AuthorPublicationDeleteView extends HttpServlet{

    private static final PublicationDAOImpl publicationDAOImpl =  new PublicationDAOImpl();
    private static final Logger logger = Logging.getLogger(AuthorPublicationDeleteView.class.getName());

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

        int publicationId = Integer.parseInt(req.getParameter("id"));
        session.setAttribute("publicationId", publicationId);
        Publication publication;

        try {
            publication = publicationDAOImpl.get(publicationId);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the publication using id. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        req.setAttribute("publication", publication);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/authorPublicationDeleteView.jsp");
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

        if(!type.equals("author")){
            resp.sendRedirect("/getPosted/login");
            return;
        }

        int publicationId = (int) session.getAttribute("publicationId");
        Publication publication = new Publication();
        publication.setId(publicationId);
        try {
            publicationDAOImpl.delete(publication);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to delete the publication. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        String thumbnailFileName = publicationId+".png";
        String pdfFileName = publicationId+".pdf";
        FileManager.deleteFile(pdfFileName, Stored.PUBLICATIONPDF);
        FileManager.deleteFile(thumbnailFileName, Stored.PUBLICATIONTHUMBNAIL);
    
        resp.sendRedirect("/getPosted/author/books");
        return;
    }
    
}
