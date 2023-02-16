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
                    <div class="container">
                        <h1 class="display-5">Edit employee data</h1>
                    </div>
                    <div class="form-control w-25 m-auto p-4">
                    <c:set var="emp" value="${Emp}"/>
                    <form action="SaveEmployee" method="post">

                        <input class="form-control" id="floatingInput" placeholder="Employee Id" name="employeeId" value="${emp.getEmployeeId()}" readonly>

                        <input type="text" class="form-control" id="floatingInput" placeholder="First name" name="firstName" value="${emp.getFirstName()}">

                        <input type="text" class="form-control" id="floatingInput" placeholder="Last name" name="lastName" value="${emp.getLastName()}">

                        <input type="text" class="form-control" id="floatingInput" placeholder="Address" name="address" value="${emp.getAddress()}">

                        <input type="text" class="form-control" id="floatingInput" placeholder="Phone Number" name="phoneNo" value="${emp.getPhoneNo()}">

                        <select name="gender" class="form-control" id="gender" required>
                            <option value="" hidden>${emp.getGender()}</option>
                            <option value="Male" <c:if test='${emp.getGender().equalsIgnoreCase("Male")}'>selected</c:if>> Male  </option>
                            <option value="Female" <c:if test='${emp.getGender().equalsIgnoreCase("Female")}'>selected</c:if> > Female  </option>
                            </select>

                            <input type="text" class="form-control" id="floatingInput" placeholder="Age" name="age" value="${emp.getAge()}">

                        <select name="departmentId" class="form-control" id="departmentId">
                            <option value="0">Select a Department</option>
                            <c:forEach var="dept" items="${DeptList}">
                                <option value=${dept.getDepartmentId()}<c:if test="${dept.getDepartmentName().equalsIgnoreCase(emp.getDepartmentName())}"> selected </c:if>> ${dept.getDepartmentName()}  </option>
                            </c:forEach>
                        </select>
                        <select name="roleId" class="form-control" id="roleId">
                            <option value="0"> Select a Role</option>
                            <c:forEach var="role" items="${RoleList}">
                                <option value=${role.getRoleId()}<c:if test="${role.getRoleName().equalsIgnoreCase(emp.getRoleName())}"> selected </c:if>> ${role.getRoleName()}  </option>
                            </c:forEach>
                        </select>

                        <input type="text" class="form-control" id="floatingInput" placeholder="basicSalary" name="basicSalary" value="${emp.getBasicSalary()}">

                        <input type="text" class="form-control" id="floatingInput" placeholder="carAllowance" name="carAllowance" value="${emp.getCarAllowance()}">

                        <input type="text" class="form-control" id="floatingInput" placeholder="SpecialAllowance" name="specialAllowance" value="${emp.getSpecialAllowance()}">

                        <button class="w-50 btn btn-lg btn-primary mt-3" type="submit">Save</button>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>