<%-- 
    Document   : login
    Created on : Dec 13, 2016, 12:56:05 PM
    Author     : riley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <title>Log in to Bookburrow</title>
    </head>
    <body>
        <jsp:useBean id="userControl" scope="session" class="wombats.Process" />
        <jsp:setProperty name="userControl" property="username" />
        <jsp:setProperty name="userControl" property="password"  />
        <%
            if(userControl.checkLogin()){
                out.print("Logged in as user " + userControl.getUsername());
            }else{
                out.print("Login Failure");
            }
        %>
    </body>
</html>
