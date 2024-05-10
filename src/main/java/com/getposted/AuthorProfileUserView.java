package com.getposted;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.Author;
import com.getposted.model.AuthorDAOImpl;
import com.getposted.model.Country;
import com.getposted.model.CountryDAOImpl;
import com.getposted.model.Language;
import com.getposted.model.LanguageDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "authorProfileUserView", urlPatterns="/authors/*")
public class AuthorProfileUserView extends HttpServlet{

    private static final Logger logger = Logging.getLogger(AuthorProfileUserView.class.getName());
    private static final AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
    private static final CountryDAOImpl countryDAOImpl = new CountryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idAString = req.getParameter("id");
        if(idAString == null){
            resp.sendRedirect("/getPosted/authors");
            return;
        }


        int id = Integer.parseInt(idAString);
        Country country;
        Author author;

        try {
            author = authorDAOImpl.get(id);
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the author using id. The error message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        try {
            country = countryDAOImpl.get(author.getCountryId());
        } catch (SQLException e) {
            logger.warning("There is an exception occoured when trying to get the publication using id. The error message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        req.setAttribute("author", author);
        req.setAttribute("country", country);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/authorProfileUserView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}
