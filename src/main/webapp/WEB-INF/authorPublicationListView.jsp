<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <style>
        #filters form{
            display: grid;
            grid-auto-flow: row;
            gap: 10px;
        }
        #books{
            display: grid;
            grid-template-columns: 1fr 1fr 1fr 1fr;
            gap: 20px;
        }
        #books img{
            width:50px;
            height: 50px;
        }
    </style>
</head>
<body>
    <a href="/getPosted/author/books/create">Create Pulications</a>
    <section id="books" >
        <c:forEach var="item" items="${publications}">
            <div>
                <img src="/getPosted/download/pulicationThumbnail/${item.id}" alt="">
                <h2>Title is ${item.title}</h2>
                <h2>Description is ${item.description}</h2>
                <h2>Price is ${item.softCopyPrice}</h2>
                <h2>Discount is ${item.softCopyDiscount}</h2>
                <h2>Publication date is ${item.publishedDate}</h2>
                <a href="/getPosted/books/?id=${item.id}">View Publication</a>
            </div>
        </c:forEach>
    </section>
</body>
</html>