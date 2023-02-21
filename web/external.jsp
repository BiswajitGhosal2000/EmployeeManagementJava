<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.User}" >
    <jsp:forward page="login.jsp"/>
</c:if>
<!doctype html>
<html lang="en">
    <head>
        <title>Employee Management Web Application</title>
        <link rel="SHORTCUT ICON" href="img/profilePhoto.png">
        <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/home.css"/>
        <!-- Custom styles for this template -->
        <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    </head>
    <body class="text-center">
        <jsp:include page="menu.jsp"></jsp:include>
            <main role="main" id = "changeableArea">
                <div class="jumbotron">
                <c:set var="msg" value="${SuccessMsg}"/>                   
                <c:if test="${msg!=null}">
                    <div class="alert alert-success msg_style" role="alert">
                        <c:out value="${msg}"/>
                    </div>
                </c:if>
                <c:set var="msg" value="${FailureMsg}"/>                   
                <c:if test="${msg!=null}">
                    <div class="alert alert-danger msg_style" role="alert">
                        <c:out value="${msg}"/>
                    </div>
                </c:if>


                <form action="AddExternal" method="post">
                    <select>
                        <option value="todos">To do</option>
                    </select>
                    <button class="btn btn-info" type="submit">Submit</button>
                </form>
            </div>
        </main>
    </body>
</html>