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
                    <div class="container">
                    <c:if test="${not empty SuccessMsg}">
                        <h1 style="color: green"><c:out value="${SuccessMsg}"/></h1>
                        <c:remove var="SuccessMsg" scope="session"/>
                        <% response.setHeader("Refresh", "3;url=home.jsp");%>
                    </c:if>
                    <c:if test="${not empty ErrorMsg}">
                        <h1 style="color: red"><c:out value="${ErrorMsg}"/></h1>
                    </c:if>
                    <h1 style="color: blue">Add New employee</h1>
                </div>
                <div class="form-control w-25 m-auto p-3">
                    <form action="AddEmployee" method="Post">
                        <input type="text" class="form-control" id="floatingInput" placeholder="First name" name="firstName" required autofocus>
                        <input type="text" class="form-control" id="floatingInput" placeholder="Last name" name="lastName" required>
                        <input type="text" class="form-control" id="floatingInput" placeholder="Address" name="address" required>
                        <input type="number" class="form-control" id="floatingInput" placeholder="Phone Number" name="phoneNo" required>
                        <select name="gender" class="form-control" id="gender" required>
                            <option value="" hidden>Select Gender</option>
                            <option value="Male"> Male  </option>
                            <option value="Female"> Female  </option>
                        </select>
                        <input type="number" class="form-control" id="floatingInput" placeholder="Age" name="age" required>
                        <select name="departmentId" class="form-control" id="departmentId" required>
                            <option value="" hidden>Select a Department</option>
                            <c:forEach var="dept" items="${DeptList}">
                                <option value="${dept.getDepartmentId()}"> ${dept.getDepartmentName()}  </option>
                            </c:forEach>
                        </select>
                        <select name="roleId" class="form-control" id="roleId" required>
                            <option value="" hidden> Select a Role</option>
                            <c:forEach var="role" items="${RoleList}">
                                <option value="${role.getRoleId()}"> ${role.getRoleName()}  </option>
                            </c:forEach>
                        </select>

                        <input type="number" class="form-control" id="floatingInput" placeholder="basicSalary" name="basicSalary" required>

                        <input type="number" class="form-control" id="floatingInput" placeholder="carAllowance" name="carAllowance" value="0" required>

                        <input type="number" class="form-control" id="floatingInput" placeholder="SpecialAllowance" name="specialAllowance" value="0" required>

                        <button class="w-50 btn btn-lg btn-info mt-5" type="submit">Save</button>
                    </form>
                </div>
                <footer class="container">
                    <h5>&copy; Exavalu 2023-24</h5>
                </footer>
            </div>         
        </main>
    </body>
</html>