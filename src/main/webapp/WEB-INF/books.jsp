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
    <section id="filters">
        <form action="/getPosted/books" method="post">
            Search<input type="text" name="search" placeholder="search">
            Date<input type="radio" name="filter" id="date" value="date">
            Size<input type="radio" name="filter" id="size" value="size">
            Radio<input type="radio" name="filter" id="softCopyPrice" value="softCopyPrice">
            Price<input type="radio" name="filter" id="softCopyDiscount" value="softCopyDiscount">
            PublishedDate<input type="radio" name="filter" id="publishedDate" value="publishedDate">
            <button type="submit">Submit</button>
        </form>
    </section>
    <section id="books" >
        <c:forEach var="item" items="${publications}">
            <div>
                <img src="/getPosted/download/pulicationThumbnail/${item.id}" alt="">
                <h2>Title is ${item.title}</h2>
                <h2>Description is ${item.description}</h2>
                <h2>Price is ${item.softCopyPrice}</h2>
                <h2>Discount is ${item.softCopyDiscount}</h2>
                <h2>Publication date is ${item.publishedDate}</h2>
            </div>
        </c:forEach>
    </section>
</body>
</html>