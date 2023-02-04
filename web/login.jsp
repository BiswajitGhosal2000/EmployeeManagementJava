<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" >
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Employee Web">
        <meta name="author" content="Biswajit Ghosal">
        <title>Employee Web</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="apple-touch-icon" href="img/profilePhoto.png" sizes="180x180">
        <link rel="icon" href="img/logo.jpg" sizes="32x32" type="image/png">
        <link rel="icon" href="img/logo.jpg" sizes="16x16" type="image/png">
        <meta name="theme-color" content="#712cf9">

        <!-- Custom styles for this template -->
        <link href="css/login.css" rel="stylesheet">
    </head>
    <body class="text-center">
        <main class="form-signin w-100 m-auto">
            <div class="card" style="width:22rem;">
                <div class="card-header">
                    <img src="img/profilePhoto.png" class="card-img-top" alt="logo">
                </div>
                <c:if test="${not empty sessionScope.LoginErrorMsg}">
                    <div class="alert alert-danger" role="alert">
                        <c:out value="${sessionScope.LoginErrorMsg}"/>
                    </div>
                </c:if>

                <div class="card-body">
                    <h1 class="card-title">Sign in </h1>
                    <form action="Login" method="Post">


                        <div class="form-floating">
                            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="emailAddress">
                            <label for="floatingInput">Email address</label>
                        </div>
                        <div class="form-floating">
                            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
                            <label for="floatingPassword">Password</label>
                        </div>
                        <div >
                            <label>
                                <input type="checkbox" value="remember-me"> Remember me
                            </label>
                        </div>
                        <button class="w-50 btn btn btn-primary" type="submit">Log in</button>
                    </form>
                    <div class="card-footer">
                        <a href="signup.jsp">Create a account</a>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
