<%-- 
    Document   : index
    Created on : May 20, 2019, 4:14:31 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form name="login" method="get"  action="\BankWeb\checkuser">
            <table bgcolor="navyblue" height="200" width="200" border="10">
                <tr>
                    <td>Username<\td>
                    <td><input type="text" name="txtuser" placeholder="enter username"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="txtpwd" placeholder="enter password"></td>
                </tr>
                <tr><td colspan="2"><input type="submit" value="Login" </td>
                </tr>
            </table>
        </form>
    </body>
</html>
