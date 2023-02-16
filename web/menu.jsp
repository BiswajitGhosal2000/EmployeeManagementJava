<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
<script>
function goTo(method)
        {
            $.ajax({
                url: method,
                success: function (responseText) {
                $("#changeableArea").html(responseText);
            }
        });
}
</script>
<style>
    a{
        cursor: pointer;
    }
</style>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="home.jsp">Employee Web</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav m-auto">
            <li class="nav-item">
                <a class="nav-link" onclick="goTo('addemployee.jsp')">Add Employee</a>
            </li>
            <li class="nav-item">
                <a class="nav-link"  onclick="goTo('showemployee.jsp')">Show Employee</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" onclick="goTo('searchemployee.jsp')">Search Employee</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" onclick="goTo('external.jsp')">Add External Data</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <a class="nav-link" href="#">
                <c:if test="${not empty User}">
                    Welcome: 
                    <%--<c:set var="Users" value='${request.getSession().getAttribute("User")}'/>--%>
                    <c:set var="user" value="${sessionScope.User}"/>
                    <c:out value = "${user.getFirstName()}"/>
                    <c:out value = "${user.getLastName()}"/>
                </c:if>
            </a>
        </form>
        <div class="text-end">
            <c:choose>
                <c:when test="${empty User}">
                    <a href="login.jsp">
                        <button type="button" class="btn btn-outline-light me-2" >Login</button>
                    </a>
                    <a href="PreSignUp">
                        <button type="button" class="btn btn-warning">Sign-up</button>
                    </a>
                </c:when>
                <c:otherwise>

                    <a href="Logout">
                        <button type="button" class="btn btn-outline-light me-2" >Log Out</button>
                    </a>
                </c:otherwise>

            </c:choose>
        </div>
    </div>
</nav>