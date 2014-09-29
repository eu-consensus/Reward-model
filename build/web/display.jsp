<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="web.Person" %>
<% Person person = (Person) request.getAttribute("person");
    String name = person.name;
    int age = Integer.parseInt(person.age);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2>Hello <%=name%> its time to make some requests!!! </h2>
        
    </body>
</html>
