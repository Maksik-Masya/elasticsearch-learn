<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Book List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme1/css/main.css" />">
</head>
<body>

<a class="btn-info" href="<c:url value="/book/edit" />">ADD BOOK</a>

<table class="margin-top-25">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Release Date</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.releaseDate}</td>
            <td>
                <div class="table-actions-btn">
                    <form style="float: left" action="<c:url value="/book/edit" />" method="GET">
                        <input type="hidden" name="id" value="${book.id}" />
                        <input type="submit" value="EDIT">
                    </form>
                    <form style="float: right" action="<c:url value="/book/delete" />" method="POST">
                        <input type="hidden" name="id" value="${book.id}" />
                        <input type="submit" value="DELETE">
                    </form>
                </div>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>