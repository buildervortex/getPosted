<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
      img {
        width: 100px;
        height: 100px;
      }
    </style>
  </head>
  <body>
    <section>
      <img src="/getPosted/download/userProfile/${user.id}" alt="" />
      <h2>Title is ${user.email}</h2>
      <h2>userName is ${user.userName}</h2>
      <h2>dob is ${user.dob}</h2>
      <h2>Name is ${user.firstName} ${user.middleName} ${user.lastName}</h2>
      <h2>Id is ${user.id}</h2>
    </section>
  </body>
</html>
