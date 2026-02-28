<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <div style="display:flex; justify-content:space-between; width: 100%;">
                <h2 style="margin:0;">@${profileUser.tagname}</h2>
                <a href="logout" style="color: #333;">Выход</a>

                <a href="feed" style="color: #333;">Лента</a>
            </div>
        </div>

        <div class="card" style="margin-bottom: 20px;">
            <p><i>${profileUser.bio}</i></p>
            <c:if test="${profileUser.id != user.id}">
                <form action="follow" method="post">
                    <input type="hidden" name="targetId" value="${profileUser.id}">
                    <input type="hidden" name="target" value="${profileUser.tagname}">
                    <c:choose>
                        <c:when test="${isFollowing}">
                            <input type="hidden" name="action" value="unfollow">
                            <button type="submit" class="btn-secondary">Отписаться</button>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="action" value="follow">
                            <button type="submit" class="btn-primary">Подписаться</button>
                        </c:otherwise>
                    </c:choose>
                </form>
            </c:if>
        </div>
        <c:if test="${profileUser.id == user.id}">
            <div class="post">
                <h3 style="margin-top:0;">Написать пост</h3>
                <form action="profile" method="post">
                    <textarea name="content" rows="3" maxlength="280" placeholder="Что у вас нового?"></textarea>
                    <button type="submit" class="btn-primary">Опубликовать</button>
                </form>
            </div>
        </c:if>

        <h3>Стена</h3>
        <c:forEach var="post" items="${posts}">
            <div class="post">
                <p style="margin:0;">${post.content}</p>
                <small style="color:gray;">${post.createdAt} | Лайков: ${post.likesCount}</small>
            </div>
        </c:forEach>
    </div>
</body>
</html>