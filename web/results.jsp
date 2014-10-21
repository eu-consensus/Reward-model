<%@page import="servlets.Printer"%>
<%@page import="java.util.List"%>
<%@page import="servlets.policy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<% List<policy> mypol = (List<policy>) request.getAttribute("List");
    String mylist = Printer.printList(mypol); 
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value='/mycss.css'/>" rel='stylesheet'>
        <title>Results</title>
    </head>
    <body>
        <h1>Results</h1>
        <%= mylist %>
    </body>
</html>
