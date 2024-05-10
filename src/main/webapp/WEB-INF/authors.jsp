<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        #search{
            display: grid;
            grid-auto-flow: column;
            gap: 10px;
        }
        #authors{
            display: grid;
            grid-template-columns: 1fr 1fr 1fr 1fr;
            gap: 20px;
        }
        #authors img{
            width:50px;
            height: 50px;
        }
    </style>
</head>
<body>
    <section id="search">
        <form action="/getPosted/authors" method="post">
            <input type="text" name="search" id="search">
            <button type="submit">Search</button>
        </form>
    </section>
    <section id="authors">
        <c:forEach var="author" items="${authors}">
            <div>
                <img src="/getPosted/download/authorProfile/${author.id}" alt="">
                <h2>Title is ${author.email}</h2>
                <h2>phoneNumber is ${author.phoneNumber}</h2>
                <h2>dob is ${author.dob}</h2>
                <h2>Name is ${author.firstName} ${author.middleName} ${author.lastName}</h2>
                <h2>userName is ${author.userName}</h2>
                <h2>${countryDAOImplObj.get(author.id).getCountry()}</h2>
                <a href="/getPosted/authors/?id=${author.id}">View Author</a>
            </div>
        </c:forEach>
    </section>
</body>
</html>