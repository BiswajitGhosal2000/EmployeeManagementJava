<%-- 
    Document   : external
    Created on : 10-Feb-2023, 7:23:26 pm
    Author     : Biswajit
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.User}" >
    <jsp:forward page="login.jsp"/>
</c:if>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">    
        <title>Add Employee Details</title>
        <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="text-center">
        <jsp:include page="menu.jsp"></jsp:include>
            <main role="main">
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


                <form action="AddExternal" method="post" id="formDetails"   >
                    <select>
                        <option value="todos">Todo</option>
                    </select>
                    <button type="submit">Submit</button>
                </form>
            </div>
        </main>
    </body>
</html>