package com.getposted.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.Category;
import com.getposted.model.CategoryDAOImpl;
import com.getposted.model.Language;
import com.getposted.model.LanguageDAOImpl;
import com.getposted.model.Publication;
import com.getposted.model.PublicationDAOImpl;
import com.getposted.model.Save;
import com.getposted.model.SaveDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "publicationUserView", urlPatterns = "/books/*")
public class PublicationUserView extends HttpServlet {

    private static Logger logger = Logging.getLogger(PublicationUserView.class.getName());
    private static String publicationUserViewJSPPath = "/WEB-INF/publicationUserView.jsp";
    private static SaveDAOImpl saveDAOImpl = new SaveDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idAsString = req.getParameter("id");
        if (idAsString == null) {
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
        
        HttpSession session = req.getSession(false);
        session.setAttribute("publicationId", id);

        try {
            publication = publicationDAOImpl.get(id);
        } catch (SQLException e) {
            logger.warning(
                    "There is an exception occoured when trying to get the publication using id. The error message is "
                            + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        try {
            category = categoryDAOImpl.get(publication.getCategoryId());
        } catch (SQLException e) {
            logger.warning(
                    "There is an exception occoured when trying to get the publication using id. The error message is "
                            + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        try {
            language = languageDAOImpl.get(publication.getLanguageId());
        } catch (SQLException e) {
            logger.warning(
                    "There is an exception occoured when trying to get the publication using id. The error message is "
                            + e.getMessage());
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
        String part = urlPart(req.getRequestURI());
        if (part.equals("cart")) {
            handleCartRequests(req, resp);
        }
    }

    private void handleCartRequests(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (!SessionManagement.isLoggedIn(session)) {
            resp.sendRedirect("/getPosted/login");
            return;
        }
        String type = (String) session.getAttribute("type");
        int id = (int) session.getAttribute("id");
        int publicationId = (int) session.getAttribute("publicationId");

        if (!type.equals("user")) {
            resp.sendRedirect("/getPosted/books/?id="+publicationId);
            return;
        }

        Save save = new Save(new Date(Calendar.getInstance().getTimeInMillis()),id,publicationId);
        try {
            saveDAOImpl.insert(save);
        } catch (SQLException e) {
            
        }
        resp.sendRedirect("/getPosted/books/?id="+publicationId);
    }

    private static String urlPart(String url){
        String urlPart = "";
        String[] parts = url.split("/");
        urlPart = parts[parts.length-1];
        return urlPart;
    }
}
