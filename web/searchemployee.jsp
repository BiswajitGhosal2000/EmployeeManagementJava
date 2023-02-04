<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.exavalu.models.Employee"%>
<%@page import="com.exavalu.services.RoleService"%>
<%@page import="com.exavalu.services.DepartmentService"%>
<%@page import="com.exavalu.models.Department"%>
<%@page import="com.exavalu.models.Role"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty sessionScope.User}" >
    <jsp:forward page="login.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Web</title>
        <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    </head>
    <body class="text-center">
        <jsp:include page="menu.jsp"></jsp:include>
            <main role="main">
                <div class="jumbotron m-auto p-5">
                    <div class="container">
                        <h1 class="display-5">Enter Details!</h1>
                    </div>
                    <div class="container" >
                        <form class="form-inline" action="SearchEmployee" method="get">
                            <input class="form-control" type="text" placeholder="First Name" name="firstName">          
                            <input class="form-control" type="text" placeholder="Last Name" name="lastName">
                            <select name="gender" class="form-control" id="gender">
                                <option value="" hidden>Gender</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                            <select name="departmentName" class="form-control" id="departmentName">
                                <option value="" hidden>Department</option>
                            <c:forEach var="dept" items="${DeptList}">
                                <option value="${dept.getDepartmentName()}"> ${dept.getDepartmentName()}  </option>
                            </c:forEach>
                        </select>
                        <select name="roleName" class="form-control" id="roleName">
                            <option value="" hidden>Role</option>
                            <c:forEach var="role" items="${sessionScope.RoleList}">
                                <option value="${role.getRoleName()}"> ${role.getRoleName()}  </option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="form-control btn btn-primary ml-2 ">Search</button>
                    </form> 
                </div>
            </div>
            <c:if test="${not empty Emps}">
                <div class="table-responsive justify-content-between">
                    
                    <div class="form-group m-auto">
                        <label>Select No of Rows</label>
                        <select class  ="form-control" name="state" id="maxRows">
                            <option value="5000">Show ALL Rows</option>
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="15">15</option>
                            <option value="20">20</option>
                            <option value="50">50</option>
                            <option value="70">70</option>
                            <option value="100">100</option>
                        </select>
                    </div>
                    <table class="table table-bordered table-hover" id="table-id">
                        <thead class="bg-info">
                            <tr>
                                <th>Employee Id</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Gender</th>
                                <th>Age</th>
                                <th>Department</th>
                                <th>Role</th>
                                <th>Basic Salary</th>
                                <th>Car Allowance</th>
                                <th>Special Allowance</th>
                                <th colspan="2">Action</th>
                            </tr>
                        </thead>
                        <tbody class="bg-light">
                            <c:forEach var="emp" items='${Emps}'>
                                <tr>
                                    <th>${emp.getEmployeeId()}</th>
                                    <td>${emp.getFirstName()}</td>
                                    <td>${emp.getLastName()}</td>
                                    <td>${emp.getAddress()}</td>
                                    <td>${emp.getPhoneNo()}</td>
                                    <td>${emp.getGender()}</td>
                                    <td>${emp.getAge()}</td>
                                    <td>${emp.getDepartmentName()}</td>
                                    <td>${emp.getRoleName()}</td>
                                    <td>${emp.getBasicSalary()}</td>
                                    <td>${emp.getCarAllowance()}</td>
                                    <td>${emp.getSpecialAllowance()}</td>
                                    <td>
                                        <a href="EditEmployee?employeeId=${emp.getEmployeeId()}">Edit</a>
                                    </td>
                                    <td>
                                        <a href="DeleteEmployee?employeeId=${emp.getEmployeeId()}">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <jsp:include page="pagination.jsp"></jsp:include>
        </main>
        <script src="js/tablepagination.js"></script>
    </body>
</html>
