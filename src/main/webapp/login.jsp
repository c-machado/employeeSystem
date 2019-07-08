<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>Login Page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form method="post" action="LoginServlet">
    <div align="center">
        <div>
            <h2>Login Page</h2>
            Please provide your credential to use this website
            <br>
            <br>
            <div style="margin-top:20px;">User Id:</div>
            <div style="margin-top:20px;">
                <input name="userId" class="form-login" title="Username" value="" size="30" maxlength="50" />
            </div>
            <div>Password:</div>
            <div>
                <input name="password" type="password" class="form-login" title="Password" value="" size="30" maxlength="48" />
            </div>
            <br />

            <input align="center" type="submit" value="Login" />
        </div>
    </div>
</form>
</body>
</html>