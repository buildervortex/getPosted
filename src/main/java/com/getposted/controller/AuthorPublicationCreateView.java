package com.getposted.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.enums.Stored;
import com.getposted.fileHandler.FileManager;
import com.getposted.logger.Logging;
import com.getposted.model.CategoryDAOImpl;
import com.getposted.model.Author;
import com.getposted.model.Category;
import com.getposted.model.CountryDAOImpl;
import com.getposted.model.Language;
import com.getposted.model.LanguageDAOImpl;
import com.getposted.model.Publication;
import com.getposted.model.PublicationDAOImpl;

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
@WebServlet(name = "authorPublicationCreateView", urlPatterns = "/author/books/create")
public class AuthorPublicationCreateView extends HttpServlet{
    private static Logger logger = Logging.getLogger(AuthorPublicationCreateView.class.getName());
    private static PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();
    private static LanguageDAOImpl languageDAOImpl = new LanguageDAOImpl();
    private static CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

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

        List<Category> categories;
        List<Language> languages;

        try {
            categories = categoryDAOImpl.getAll();
        } catch (SQLException e) {
            logger.warning("There is an exception happend when trying to get all categories. the exception is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        try {
            languages = languageDAOImpl.getAll();
        } catch (SQLException e) {
            logger.warning("There is an exception happend when trying to get all languages. the exception is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        req.setAttribute("categories", categories);
        req.setAttribute("languages", languages);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/authorPublicationCreateView.jsp");
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
        handlePublicationCreate(req, resp, id);
        return;
    }

    private void handlePublicationCreate(HttpServletRequest req, HttpServletResponse resp, int authorId) throws IOException, ServletException{
        String description = req.getParameter("description");
        String size = req.getParameter("size");
        String pdfPath = "";
        String softCopyPrice = req.getParameter("softCopyPrice");
        String pageCount = req.getParameter("pageCount");
        String softCopyDiscount = req.getParameter("softCopyDiscount");
        String title = req.getParameter("pdfTitle");
        String publishedDate = req.getParameter("publishedDate");
        String categoryId = req.getParameter("categoryId");
        String languageId = req.getParameter("languageId");
        
        if(!checkForNullabilityOrEmphty(softCopyDiscount,title,softCopyPrice,pageCount,size,categoryId,languageId)){
            resp.sendRedirect("/getPosted/author/books/create");
            return;
        }

        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        Publication publication = new Publication(0,description,date,Integer.parseInt(size),pdfPath,Double.parseDouble(softCopyPrice),Integer.parseInt(pageCount),Double.parseDouble(softCopyDiscount),title,Date.valueOf(publishedDate),Integer.parseInt(categoryId),Integer.parseInt(languageId),authorId);
        try {
            publicationDAOImpl.insert(publication);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to create the publication. the exception is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        try {
            publication = publicationDAOImpl.getAllPublicationsForGivenTitlePattern(title).get(0);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the publication. the exception is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        fileUploadHandler(req, resp, "pdfFile", publication,Stored.PUBLICATIONPDF,".pdf");
        fileUploadHandler(req, resp, "thumbnail", publication,Stored.PUBLICATIONTHUMBNAIL,".png");
        resp.sendRedirect("/getPosted/author/books");
        return;
    }
    
    private boolean checkForNullabilityOrEmphty(String... vars){
        boolean isValid = true;

        for(String i: vars){
            if( i == null || i.isEmpty())
            {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    private long fileUploadHandler(HttpServletRequest request, HttpServletResponse response, String fileName, Publication publication,String stored, String extenstion) throws IOException, ServletException{
        long readSize = 0;
        Part filePart = request.getPart(fileName);
        if(filePart == null || filePart.getSize() == 0)return readSize;

        String storingFileName = publication.getId()+extenstion;

        InputStream inputStream = filePart.getInputStream();
        readSize = FileManager.storeFile(storingFileName,inputStream,stored);
        return readSize;
    }
}