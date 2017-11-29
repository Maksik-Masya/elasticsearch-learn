<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <style>
        input[type=text], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .control-buttons {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
        .save {
            width: 100px;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .save:hover {
            background-color: #45a049;
        }

        .cancel {
            width: 100px;
            background-color: #fe1408;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .cancel:hover {
            background-color: #c21909;
        }

    </style>
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
        <input class="cancel" type="button" value="Cancel">
    </div>
</form>

</body>
</html>