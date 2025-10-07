<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Movies List</title>
</head>
<body>
<h2>🎬 Movie Ticket Booking</h2>
<a href="add">➕ Add New Movie</a><br><br>

<table border="1" cellpadding="8">
    <tr>
        <th>ID</th><th>Title</th><th>Genre</th><th>Price</th>
    </tr>
    <c:forEach var="movie" items="${movies}">
        <tr>
            <td>${movie.id}</td>
            <td>${movie.title}</td>
            <td>${movie.genre}</td>
            <td>${movie.price}</td>
        </tr>
    </c:forEach>

</body>
</html>
