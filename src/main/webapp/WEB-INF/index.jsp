<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import='com.getposted.controller.SessionManagement' %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        #Carousel{
            display: grid;
            grid-template-columns: 1fr 1fr 1fr 1fr;
        }
        #Carousel img{
            width:100px;
            height: 100px;
        }
        #navBar {
            display: grid;
            grid-auto-flow: column;
            gap: 1fr 1fr;
        }
    </style>
</head>
<body>
    <h1>Navigation Bar</h1>
    <section id="navBar">
        <a href="/getPosted/">Home</a>
        <a href="">About Us</a>
        <a href="">Authors</a>
        <a href="/getPosted/books">Books</a>

        <% if(SessionManagement.isLoggedIn(session)) { %>
            <a href="/getPosted/logout/">LogOut</a>
        <% } else { %>
            <a href="/getPosted/login">Login</a>
            <a href="/getPosted/singup?type=user">Sing up As User</a>
            <a href="/getPosted/singup?type=author">Sing up As Author</a>
        <% } %>

    </section>
    <h1>Carousel</h1>
    <section id="Carousel">
        <img src="/getPosted/download/carosalPicture/1">
        <img src="/getPosted/download/carosalPicture/2">
        <img src="/getPosted/download/carosalPicture/4">
        <img src="/getPosted/download/carosalPicture/5">
    </section>
    <h1>Top Authors</h1>
    <section id="TopAuthors">
        <c:forEach var="item" items="${authors}">
            <div>
                ${item.firstName}
            </div>
        </c:forEach>
    </secion>
    <h1>Top Publications</h1>
    <section id="TopPublications">
        <c:forEach var="item" items="${publications}">
            <div>
                ${item.title}
            </div>
        </c:forEach>
    </secion>
    <h1>Top Categories</h1>
    <section id="TopCategories">
        <c:forEach var="item" items="${categories}">
            <div>
                ${item.category}
            </div>
        </c:forEach>
    </secion>
</body>
</html>