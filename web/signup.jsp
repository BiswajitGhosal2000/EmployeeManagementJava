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
            <!---->
            <form action="PreSignUp" method="Post" id="signupform">
                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@domain.com" name="emailAddress" value="${User.emailAddress}" required>
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" value="${User.password}" required>
                    <label for="floatingPassword">Password</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="First Name" name="firstName" value="${User.firstName}"required>
                    <label for="floatingInput">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Password" name="lastName" value="${User.lastName}" required>
                    <label for="floatingInput">Last Name</label>
                </div>
                <div class="form-floating">
                    <select name="countryCode" class="form-select" id="countryCode" onchange="fetchContent('countryCode', 'stateCode')">
                        <option value="">Select a Country</option>
                        <c:forEach items="${CountryList}" var="country">
                            <option value='${country.getCountryCode()}' <c:if test="${country.getCountryCode() == User.getCountryCode()}" > selected </c:if>> 
                                ${country.getCountryName()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-floating">
                    <select name="stateCode" class="form-select" id="stateCode" onchange="fetchContent('stateCode', 'districtCode')">
                        <option value="" hidden>Select a State</option>
                    </select>
                </div>
                <div class="form-floating">
                    <select name="districtCode" class="form-select" id="districtCode" required>
                        <option value="" hidden>Select a District</option>
                    </select>
                </div>
                <button class="w-100 btn btn btn-primary" type="submit">Submit</button>
            </form>
            <a href="home.jsp">Go Back</a>
        </main>
        <script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
        <script>
            function fetchContent(selectedId, targetId)
                {
                $.ajax({
                    url: 'PreSignUp',
                    data: {
                        [selectedId]: $("#" + selectedId).val()
                    },
                    success: function (responseText) {
                        $("#" + targetId).html(responseText);
                    }
                });
                }
        </script>
    </body>
</html>
