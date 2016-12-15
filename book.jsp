<%-- 
    Document   : book
    Created on : Dec 14, 2016, 6:41:19 PM
    Author     : skrejci
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="wombats.Book"%>
<%@page import="wombats.Review"%>
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
            ArrayList<Book> resultList = searchControl.searchBooks();
            Book book = resultList.get(0);
            out.print("<p>Title: " + book.getTitle() + "<br>");
            out.print("Author: " + book.getAuthor() + "<br>");
            out.print("Genre: " + book.getGenre() + "</p>");
            out.print("Reviews: ");
            ArrayList<Review> reviews = book.getReviews();
            for (Review r : reviews) {
                out.print("<p>" + r.getRating() + "/10<br>");
                out.print(r.getContent() + "</p>");
            }
            %>
        </div>
    </body>
</html>
