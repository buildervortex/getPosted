package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.getposted.enums.Stored;
import com.getposted.fileHandler.FileManager;
import com.getposted.logger.Logging;
import com.getposted.model.Author;
import com.getposted.model.AuthorDAOImpl;
import com.getposted.model.Publication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "authorProfileDeleteView", urlPatterns = "/author/delete")
public class AuthorProfileDeleteView extends HttpServlet{

    private static final Logger logger = Logging.getLogger(AuthorProfileDeleteView.class.getName());
    private static final AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();

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
        try {
            author = authorDAOImpl.get(id);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when tring to get the author by id. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        req.setAttribute("author", author);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/authorProfileDeleteView.jsp");
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

        Author author = new Author();
        author.setId(id);
        try {
            authorDAOImpl.delete(author);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to delete the author. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        String authorProfileFileName = id+".png";
        FileManager.deleteFile(authorProfileFileName, Stored.AUTHORPROFILEPICTURE);

        resp.sendRedirect("/getPosted/logout/");
        return;
    }
    
}
