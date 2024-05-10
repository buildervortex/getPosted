<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        form{
            display: grid;
            grid-auto-flow: row;
            gap: 10px;
        }
        img{
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
    <section>
        <form action="/getPosted/user/update" method="post">
            <img src="/getPosted/download/userProfile/${user.id}" alt="" />
            <input type="file" name="file" id="file">
            <input type="email" name="email" id="email" value="${user.email}">
            <input type="password" name="password" id="password">
            <input type="text" name="userName" id="userName" value="${user.userName}">
            <input type="date" name="dob" id="dob" value="${user.dob}">
            <input type="text" name="firstName" id="firstName" value="${user.firstName}">
            <input type="text" name="middleName" id="middleName" value="${user.middleName}">
            <input type="text" name="lastName" id="lastName" value="${user.lastName}">
            <button type="submit">Submit</button>
            <button type="reset">Reset</button>
        </form>
      </section>
</body>
</html>