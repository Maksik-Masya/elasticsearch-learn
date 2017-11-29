<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<a href="<c:url value="/book/edit" />">ADD BOOK</a>

<table>
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
                <a href="<c:url value="/book/edit"><c:param name="id" value="${book.id}" /></c:url>">EDIT BOOK</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>