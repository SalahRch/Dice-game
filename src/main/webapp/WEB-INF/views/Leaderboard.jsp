<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leaderboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2>Leaderboard</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Username</th>
                    <th scope="col">Best Score</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <tr>
                        <th scope="row">${status.count}</th>
                        <td>${user.username}</td>
                        <td>${user.bestscore}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <form action="${pageContext.request.contextPath}/Game" method="get">
                <button type="submit" class="btn btn-primary" >Play</button>
            </form>
            <form action="${pageContext.request.contextPath}/disconnect" method="post">
                <button type="submit" class="btn btn-primary" >Disconnect</button>
            </form>
        </div>
    </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
