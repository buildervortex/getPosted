<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        section{
            display: grid;
            grid-auto-flow: row;
            gap:10px;
        }
        div{
            display: grid;
            grid-auto-flow: column;
        }
        img{
            height:50px;
            width: 50px;
        }
    </style>
</head>
<body>
    <section id="books" >
        <c:forEach var="item" items="${publications}">
            <div>
                <img src="/getPosted/download/pulicationThumbnail/${item.id}" alt="">
                <h2>Title is ${item.title}</h2>
                <h2>Price is ${item.softCopyPrice}</h2>
                <h2>Discount is ${item.softCopyDiscount}</h2>
                <a href="/getPosted/books/?id=${item.id}">View Publication</a>
                <input type="checkbox" name="checkedIds" id="checkedIds">
            </div>
        </c:forEach>
    </section>
</body>
</html>