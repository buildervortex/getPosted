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
            height: 100px
        }
    </style>
</head>
<body>
    <section>
        <img src="/getPosted/download/pulicationThumbnail/${publication.id}" alt="">
        <h1>Publication id ${publication.id}</h1>
        <h1>Description is ${publication.description}</h1>
        <h1>Published Date is ${publication.date}</h1>
        <h1>Pdf size is ${publication.size}</h1>
        <h1>Pdf softCopy price is ${publication.softCopyPrice}</h1>
        <h1>Pdf page count is ${publication.pageCount}</h1>
        <h1>Pdf softCopyDiscount is ${publication.softCopyDiscount}</h1>
        <h1>Title is ${publication.title}</h1>
        <h1>Pdf publishedDate is ${publication.publishedDate}</h1>
        <h1>Pdf category is ${category.category}</h1>
        <h1>Pdf language is ${language.language}</h1>
    </section>
    <a href="/getPosted/author/books/update/?id=${publication.id}">Update Publication</a>
    <a href="/getPosted/author/books/delete/?id=${publication.id}">Delete Publication</a>
</body>
</html>