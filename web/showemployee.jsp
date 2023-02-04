<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.exavalu.services.EmployeeService"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.User}" >
    <jsp:forward page="login.jsp"/>
</c:if>
<!doctype html>
<html lang="en">
    <head>
        <title>Employee Management Web Application</title>
        <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/product/">
        <link rel="SHORTCUT ICON" href="img/profilePhoto.png">
        <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    </head>
    <body class="text-center">
        <jsp:include page="menu.jsp"></jsp:include>
            <main role="main">
                <div class="jumbotron">
                    <h2>Select Number Of Rows</h2><div class="form-group">     <!--        Show Numbers Of Rows         --><select class  ="form-control" name="state" id="maxRows"><option value="5000">Show ALL Rows</option><option value="5">5</option><option value="10">10</option><option value="15">15</option><option value="20">20</option><option value="50">50</option><option value="70">70</option><option value="100">100</option></select>                 </div>
                    <table id="table-id" class="table table-bordered table-hover"  cellspacing="0" width="100%">
                        <thead class="bg-success">
                            <c:choose>
                                <c:when test = "${requestScope.noData != null}">
                                    <tr>
                                        <td colspan="5">
                                            <h2 style="border:2px solid rgb(255, 99, 71); background-color:rgba(255, 99, 71, 0.5); font-size:15px;">
                                                <c:out value="${requestScope.noData}"> </c:out>
                                                </h2>
                                            </td>
                                        </tr>
                                </c:when>
                            </c:choose>
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
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="emp" items="${EmpList}">
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
                                    <a href="DeleteEmployee?employeeId=${emp.getEmployeeId()}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <jsp:include page="pagination.jsp"></jsp:include>
        </main>
        <script src="js/tablepagination.js"></script>
    </body>
</html>