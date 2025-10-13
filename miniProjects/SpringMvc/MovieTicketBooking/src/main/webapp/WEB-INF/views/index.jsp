<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Spring 6 Demo</title>
</head>
<body>
    <h1>${message}</h1>

    <p>Example with JSTL:</p>
    <ul>
        <c:forEach var="i" begin="1" end="5">
            <li>Item ${i}</li>
        </c:forEach>
    </ul>
</body>
</html>
