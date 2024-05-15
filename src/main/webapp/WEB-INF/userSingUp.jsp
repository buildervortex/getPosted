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
    </style>

    <c:set var="email" value="${empty param.email ? '':param.email}"/>
    <c:set var="userName" value="${empty param.userName? '':param.userName}"/>
    <c:set var="dob" value="${empty param.dob ? '':param.dob}"/>
    <c:set var="firstName" value="${empty param.firstName ? '':param.firstName}"/>
    <c:set var="middleName" value="${empty param.middleName ? '':param.middleName}"/>
    <c:set var="lastName" value="${empty param.lastName ? '':param.lastName}"/>
</head>
<body>
    <h1>User Sing up</h1>
    <form action="/getPosted/singup" method="post">
        <input type="hidden" name="type" value="user">
        <input type="email" name="email" id="email" placeholder="email" value="${email}">
        <input type="password" name="password" id="password" placeholder="password">
        <input type="text" name="userName" id="userName" placeholder="userName" value="${userName}">
        <input type="date" name="dob" id="dob" placeholder="dob" value="${dob}">
        <input type="text" name="firstName" id="firstName" placeholder="firstName" value="${firstName}">
        <input type="text" name="middleName" id="middleName" placeholder="middleName" value="${middleName}">
        <input type="text" name="lastName" id="lastName" placeholder="lastName" value="${lastName}">

        <button type="reset">Reset</button>
        <button type="submit">Submit</button>
    </form>
</body>
</html>