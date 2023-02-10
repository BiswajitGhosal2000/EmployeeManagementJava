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
                <%--<c:set var="user" value="${User}"></c:set>--%>
                    <div class="form-floating">
                        <select name="countryCode" class="form-control" id="countryCode" onchange="getsignUp()" required>
                            <option value="" hidden>Select Country</option>
                        <c:forEach var="country" items="${CountryList}">
                            <option value=${country.getCountryCode()}<c:if test="${country.getCountryCode().equalsIgnoreCase(User.getCountryCode())}"> selected </c:if>> ${country.getCountryName()}  </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-floating">
                    <select name="stateCode" class="form-control" id="stateCode" onchange="getsignUp()" required>
                        <option value="" hidden>Select State</option>
                        <c:forEach var="state" items="${StateList}">
                            <option value=${state.getStateCode()}<c:if test="${state.getStateCode().equalsIgnoreCase(User.getStateCode())}"> selected </c:if>> ${state.getStateName()}  </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-floating">
                    <select name="districtCode" class="form-control" id="districtCode" required>
                        <option value="" hidden>Select District</option>
                        <c:forEach var="dist" items="${DistList}">
                            <option value=${dist.getDistrictCode()}<c:if test="${dist.getDistrictCode().equalsIgnoreCase(User.getDistrictCode())}"> selected </c:if>> ${dist.getDistrictName()}  </option>
                        </c:forEach>
                    </select>
                </div>
                <button class="w-100 btn btn btn-primary" type="submit">Submit</button>
            </form>
            <a href="home.jsp">Go Back</a>
        </main>
        <script>
            function getsignUp() {
                signupform.submit();
            }
        </script>
    </body>
</html>
