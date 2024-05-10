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
</head>
<body>
    <form action="/getPosted/login" method="post">
        <input type="email" name="email" id="email" placeholder="email">
        <input type="password" name="password" id="password" placeholder="password">
        <input type="checkbox" name="type" id="type" value="author">
        <label for="type">Login As Author</label>
        <a href="/getPosted/singup?type=user">Sing up As User</a>
        <a href="/getPosted/singup?type=author">Sing up As Author</a>
        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
    </form>
</body>
</html>