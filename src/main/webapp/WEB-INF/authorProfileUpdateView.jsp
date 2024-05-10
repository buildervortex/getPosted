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
        #update form{
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
    <img src="/getPosted/download/authorProfile/${author.id}" alt="">
    <section id="update">
        <form method="post" enctype="multipart/form-data">
            <input type="file" name="file" id="file">
            <input type="email" name="email" id="email" placeholder="email" value="${author.email}">
            <input type="text" name="phoneNumber" id="phoneNumber" placeholder="phoneNumber" value="${author.phoneNumber}">
            <textarea name="bio" id="" cols="30" rows="10" placeholder="bio">${author.bio}</textarea>
            <input type="password" name="password" id="password" placeholder="password">
            <input type="date" name="dob" id="dob" placeholder="dob" value="${author.dob}">
            <input type="text" name="firstName" id="firstName" placeholder="firstName" value="${author.firstName}">
            <input type="text" name="middleName" id="middleName" placeholder="middleName" value="${author.middleName}">
            <input type="text" name="lastName" id="lastName" placeholder="lastName" value="${author.lastName}">
            <input type="text" name="userName" id="userName" placeholder="userName" value="${author.userName}">
            <select name="countryId" id="countryId">
                <c:forEach var="country" items="${countries}">
                    <option value="${country.id}">${country.country}</option>
                </c:forEach>
            </select>
            <button type="reset">Reset</button>
            <button type="submit">Submit</button>
        </form>
    </section>
</body>
</html>