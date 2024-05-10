package com.getposted.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.Author;
import com.getposted.model.AuthorDAOImpl;
import com.getposted.model.Category;
import com.getposted.model.CategoryDAOImpl;
import com.getposted.model.Publication;
import com.getposted.model.PublicationDAOImpl;
import com.getposted.model.PurchaseDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HomePageServlet", urlPatterns = "/")
public class HomePage extends HttpServlet {

    private static Logger logger = Logging.getLogger(HomePage.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get All Top 10 authors, publications and categories
        List<Author> authors = getAllTopAuthors(10);
        List<Publication> publications = getAllTopPublications(10);
        List<Category> categories = getAllTopCategories(10);

        // pack all the data for dispachings
        req.setAttribute("authors", authors);
        req.setAttribute("publications", publications);
        req.setAttribute("categories", categories);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(req, resp);
    }

    private List<Author> getAllTopAuthors(int numberOfAuthors) {
        List<Author> authors = null;
        List<Integer> authorIds = null;
        PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();
        AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();

        try {
            // get list of author ids
            authorIds = purchaseDAOImpl.getListOfAuthorIdsOrderedInSelling(true, numberOfAuthors);
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is an erro happened in the %s class when getting all authorIds. The error message is %s",
                    HomePage.class.getName(), e.getMessage()));
            throw new RuntimeException(e.getMessage());
        }

        try {
            // get list of authors
            authors = authorDAOImpl
                    .getAuthorsByGivenAuthorIds(authorIds.stream().mapToInt(Integer::intValue).toArray());
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is an erro happened in the %s class when getting all authors. The error message is %s",
                    HomePage.class.getName(), e.getMessage()));
            throw new RuntimeException(e.getMessage());
        }

        return authors;
    }

    private List<Publication> getAllTopPublications(int numberOfPublications) {
        List<Publication> publications = null;
        List<Integer> publicationIds = null;
        PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();
        PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();

        try {
            publicationIds = purchaseDAOImpl.getListOfPublicationIdsOrderedInSelling(true, numberOfPublications);
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is an erro happened in the %s class when getting all publicationIds. The error message is %s",
                    HomePage.class.getName(), e.getMessage()));
            throw new RuntimeException(e.getMessage());
        }
        try {
            // get list of authors
            publications = publicationDAOImpl.getPublicationsByGivenPublicationIds(
                    publicationIds.stream().mapToInt(Integer::intValue).toArray());
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is an erro happened in the %s class when getting all publications. The error message is %s",
                    HomePage.class.getName(), e.getMessage()));
            throw new RuntimeException(e.getMessage());
        }

        return publications;
    }

    private List<Category> getAllTopCategories(int numberOfCategories) {
        List<Category> categories = null;
        List<Integer> categoryIds = null;
        CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
        PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();

        try {
            categoryIds = purchaseDAOImpl.getListOfCategoryIdsOrderedInSelling(true, numberOfCategories);
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is an erro happened in the %s class when getting all categoryIds. The error message is %s",
                    HomePage.class.getName(), e.getMessage()));
            throw new RuntimeException(e.getMessage());
        }
        try {
            // get list of authors
            categories = categoryDAOImpl.getCategoriesByGivenIds(
                    categoryIds.stream().mapToInt(Integer::intValue).toArray());
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is an erro happened in the %s class when getting all categories. The error message is %s",
                    HomePage.class.getName(), e.getMessage()));
            throw new RuntimeException(e.getMessage());
        }

        return categories;
    }
}
