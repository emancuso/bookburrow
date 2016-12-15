<%-- 
    Document   : search
    Created on : Dec 13, 2016, 8:51:31 PM
    Author     : riley
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="wombats.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <jsp:useBean id="searchControl" scope="session" class="wombats.Process" />
        <jsp:setProperty name="searchControl" property="key" />
        <div>
            <% 
            //out.print(searchControl.getKey());
            ArrayList<Book> resultList = searchControl.searchBooks();
            for (Book book : resultList) {
                out.print("<p><a href='book.jsp?key=" + book.getTitle() + "'>" + book.getTitle() + "</a><br>");
                out.print(book.getAuthor() + "</p>");
            }
            %>
        </div>
    </body>
</html>
