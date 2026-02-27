<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container" style="padding-top: 50px;">
        <div class="card">
            <h2 style="text-align:center;">Вход</h2>
            <form action="login" method="post">
                <input type="text" name="tagname" placeholder="@tagname" required>
                <input type="password" name="password" placeholder="Пароль" required>
                <button type="submit" class="btn-primary" style="width: 100%;">Войти</button>
            </form>
            <p class="error">${error}</p>
            <div style="text-align: center; margin-top: 10px;">
                <a href="register">Регистрация</a>
            </div>
        </div>
    </div>
</body>
</html>