<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        img{
            width: 100px;
            height: 100px;
        }
        body{
            display: grid;
            gap: 10px;
        }
    </style>
</head>
<body>
    <nav>
        <a href="/getPosted/logout/">LogOut</a>
    </nav>
    <section>
        <img src="/getPosted/download/authorProfile/${author.id}" alt="">
        <h2>Id is ${author.id}</h2>
        <h2>email is ${author.email}</h2>
        <h2>phoneNumber is ${author.phoneNumber}</h2>
        <h2>Bio is ${author.bio}</h2>
        <h2>dob is ${author.dob}</h2>
        <h2>Name is ${author.firstName} ${author.middleName} ${author.lastName}</h2>
        <h2>userName is ${author.userName}</h2>
        <h2>${country.country}</h2>
    </section>
    <a href="/getPosted/author/update">Update</a>
    <a href="/getPosted/author/delete">Delete</a>
    <a href="/getPosted/author/books">View Pulications</a>
</body>
</html>