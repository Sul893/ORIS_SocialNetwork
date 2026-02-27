<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Лента</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h2 style="margin:0;">Лента</h2>
            <div>
                <a href="profile">Профиль</a> |
                <a href="logout" style="color: #333;">Выход</a>
            </div>
        </div>

        <c:forEach var="post" items="${posts}">
            <div class="post">
                <div>
                    <a href="profile?u=${post.authorTagname}">@${post.authorTagname}</a>
                    <small style="color:gray; float:right;">${post.createdAt}</small>
                </div>
                <div class="post-content">${post.content}</div>

                <div class="post-footer">
                    <form action="like" method="post" style="display:inline;">
                        <input type="hidden" name="postId" value="${post.id}">
                        <c:choose>
                            <c:when test="${post.liked}">
                                <input type="hidden" name="action" value="unlike">
                                <button type="submit" class="liked">♥ ${post.likesCount}</button>
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="action" value="like">
                                <button type="submit" class="btn-like">♡ ${post.likesCount}</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                    <a href="comments?postId=${post.id}">Комментарии</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>