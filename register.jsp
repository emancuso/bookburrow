<%-- 
    Document   : register
    Created on : Dec 13, 2016, 12:56:13 PM
    Author     : riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register for Bookburrow!</title>
    </head>
    <body>
        <jsp:useBean id="userControl" scope="session" class="wombats.Process" />
        <jsp:setProperty name="userControl" property="username" />
        <jsp:setProperty name="userControl" property="password"  />
        <jsp.setProperty name="userControl" property="passConfirm" />
        <%
            try {
                userControl.createUser();
                out.print("Registration Successful!");
                if(userControl.checkLogin()){
                    out.print("Logged in as user " + userControl.getUsername());
                }else{
                    out.print("Login Failure");
                }
            } catch (Exception e) {
                out.print("Registration Failure");
            }
        %>
    </body>
</html>
