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
        #create form{
            display: grid;
            grid-auto-flow: row;
            gap: 10px;
        }
    </style>
</head>
<body>
    <nav>
        <a href="/getPosted/logout/">LogOut</a>
    </nav>
    <section id="create">
        <form action="/getPosted/author/books/create" method="post" enctype="multipart/form-data">
            <input type="text" name="pdfTitle" id="title" placeholder="title">
            <input type="file" name="pdfFile" id="file">
            <input type="file" name="thumbnail" id="thumbnail">
            <input type="text" name="description" id="description" placeholder="description">
            <input type="text" name="size" id="size" placeholder="size">
            <input type="text" name="softCopyPrice" id="softCopyPrice" placeholder="softCopyPrice">
            <input type="number" name="pageCount" id="pageCount" placeholder="pageCount">
            <input type="text" name="softCopyDiscount" id="softCopyDiscount" placeholder="softCopyDiscount">
            <input type="date" name="publishedDate" id="publishedDate" placeholder="publishedDate" >
            <select name="languageId" id="languageId">
                <c:forEach var="language" items="${languages}">
                    <option value="${language.id}">${language.language}</option>
                </c:forEach>
            </select>
            <select name="categoryId" id="categoryId">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.category}</option>
                </c:forEach>
            </select>
            <button type="reset">Reset</button>
            <button type="submit">Submit</button>
        </form>
    </section>
</body>
</html>