<%-- 
    Document   : signup
    Created on : 19-Jan-2023, 3:19:26 pm
    Author     : Biswajit Ghosal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
        <link href="https://getbootstrap.com/docs/5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/signin.css" rel="stylesheet">
    </head>
    <body class="text-center">
        <main class="form-signin w-100 m-auto">
            <h1 class="display-3">Register!!</h1>
            <c:if test="${not empty AlreadyExist}">
            <div class="alert alert-danger" role="alert">
                <c:out value="${AlreadyExist}"/>
                <c:remove var="AlreadyExist"/>
            </div>
            </c:if>
            <form action="Signup" method="Post">
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="First Name" name="firstName" required="true">
                    <label for="floatingInput">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Password" name="lastName" required="true">
                    <label for="floatingInput">Last Name</label>
                </div>
                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@domain.com" name="emailAddress" required="true">
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" required="true">
                    <label for="floatingPassword">Password</label>
                </div>
                <button class="w-100 btn btn btn-primary" type="submit">Submit</button>
            </form>
            <a href="login.jsp">Go Back</a>
        </main>
    </body>
</html>
