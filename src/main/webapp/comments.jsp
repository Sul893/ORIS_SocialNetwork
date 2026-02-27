<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Комментарии</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <a href="feed">Назад к ленте</a>

        <div class="post" style="margin-top: 20px;">
            <p><b>@${post.authorTagname}</b></p>
            <p style="font-size: 1.2em;">${post.content}</p>
        </div>

        <div class="card">
            <h3 style="margin-top:0;">Оставить комментарий</h3>
            <form action="comments" method="post">
                <input type="hidden" name="postId" value="${post.id}">
                <textarea name="content" rows="2" maxlength="280" placeholder="Ваш комментарий..."></textarea>
                <button type="submit" class="btn-primary">Отправить</button>
            </form>
        </div>

        <h3>Обсуждение</h3>
        <c:forEach var="comment" items="${comments}">
            <div class="comment">
                <c:if test="${not empty comment.parentAuthorTagname}">
                    <span class="reply-tag">Ответ для @${comment.parentAuthorTagname}</span>
                </c:if>

                <p style="margin:5px 0;">
                    <b>@${comment.authorTagname}</b>
                    <small style="color:gray;">${comment.createdAt}</small>
                </p>
                <p style="margin:5px 0;">${comment.content}</p>

                <form action="comments" method="post" style="margin-top: 5px;">
                    <input type="hidden" name="postId" value="${post.id}">
                    <input type="hidden" name="parentId" value="${comment.id}">
                    <input type="text" name="content" placeholder="Ответить..." style="width: 80%;">
                    <button type="submit" class="btn-secondary" style="padding: 5px;">></button>
                </form>
            </div>
        </c:forEach>
    </div>
</body>
</html>