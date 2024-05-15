package com.getposted.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;
import com.getposted.model.Publication;
import com.getposted.model.PublicationDAOImpl;
import com.getposted.model.Save;
import com.getposted.model.SaveDAOImpl;
import com.getposted.model.User;
import com.getposted.model.UserDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "usercart", urlPatterns = "/user/cart")
public class userCartView extends HttpServlet {

    private static final Logger logger = Logging.getLogger(UserProfileUpdateView.class.getName());
    private static final UserDAOImpl userDAOImpl = new UserDAOImpl();
    private static final PublicationDAOImpl publicationDAOImpl = new PublicationDAOImpl();
    private static final SaveDAOImpl saveDAOImpl = new SaveDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(! SessionManagement.isLoggedIn(session)){
            resp.sendRedirect("/getPosted/login");
            return;
        }
        String type = (String) session.getAttribute("type");
        int id = (int) session.getAttribute("id");

        if(!type.equals("user")){
            resp.sendRedirect("/getPosted/login");
            return;
        }

        List<Save> saves;
        try {
            saves = saveDAOImpl.getList(id);
        } catch (SQLException e) {
            logger.warning("There is an exception happened when trying to get the saves by user id. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        };

        List<Publication> publications = new ArrayList<Publication>();
        for(Save save: saves){
            try {
                publications.add(publicationDAOImpl.get(save.getPublicationId()));
            } catch (SQLException e) {
                logger.warning("There is an exception happened when trying to get the publication by id. The exception message is "+e.getMessage());
            throw new RuntimeException(e.getMessage());
            }
        }

        req.setAttribute("publications", publications);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/userCartView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
