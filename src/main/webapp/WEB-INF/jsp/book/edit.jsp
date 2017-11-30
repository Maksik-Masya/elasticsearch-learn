<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme1/css/main.css" />">
</head>
<body>
<form action="<c:url value="/book/save" />" method="post">

    <h3>Title:</h3>
    <input type="text" name="title" value="<c:if test="${book ne null}">${book.title}</c:if>" />

    <h3>Author:</h3>
    <input type="text" name="author" value="<c:if test="${book ne null}">${book.author}</c:if>" />

    <h3>Release Date:</h3>
    <input type="text" name="releaseDate" value="<c:if test="${book ne null}">${book.releaseDate}</c:if>" />
    <br><br>

    <input type="hidden" name="id" value="<c:if test="${book ne null}">${book.id}</c:if>" />

    <div class="control-buttons">
        <input class="save" type="submit" value="Save">
        <a class="btn-cancel" href="<c:url value="/book/list" />">Cancel</a>
    </div>
</form>

</body>
</html>