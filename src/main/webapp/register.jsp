<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container" style="padding-top: 30px;">
        <div class="card">
            <h2 style="text-align:center;">Регистрация</h2>
            <form action="register" method="post">
                <input type="text" name="tagname" placeholder="@tagname" required>
                <input type="password" name="password" placeholder="Пароль" required>
                <textarea name="bio" rows="3" placeholder="О себе (необязательно)"></textarea>
                <button type="submit" class="btn-primary" style="width: 100%;">Зарегистрироваться</button>
            </form>
            <p class="error">${error}</p>
            <div style="text-align: center; margin-top: 10px;">
                <a href="login">Назад ко входу</a>
            </div>
        </div>
    </div>
</body>
</html>