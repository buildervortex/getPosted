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
    </style>
</head>
<body>
    <section>
        <img src="/getPosted/download/authorProfile/${author.id}" alt="">
        <h2>Title is ${author.email}</h2>
        <h2>phoneNumber is ${author.phoneNumber}</h2>
        <h2>dob is ${author.dob}</h2>
        <h2>Name is ${author.firstName} ${author.middleName} ${author.lastName}</h2>
        <h2>userName is ${author.userName}</h2>
        <h2>${country.country}</h2>
    </section>
</body>
</html>