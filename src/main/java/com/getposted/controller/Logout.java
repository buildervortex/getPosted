package com.getposted.controller;

import java.io.IOException;

import com.getposted.enums.UrlPatterns;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "logout", urlPatterns ="/logout/*")
public class Logout extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(! SessionManagement.isLoggedIn(session)) return;

        session.removeAttribute("id");
        session.removeAttribute("type");

        resp.sendRedirect(UrlPatterns.HomePage);
        return;
    }
}
