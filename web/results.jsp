<%@page import="servlets.Printer"%>
<%@page import="java.util.List"%>
<%@page import="servlets.policy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<% List<policy> mypol = (List<policy>) request.getAttribute("List");

    String mylist = "";
    if (mypol != null) {
        mylist = Printer.printList(mypol);
    }

    List<policy> mypol2 = (List<policy>) request.getAttribute("List2");
    String mylist2 = "";
    if (mypol2 != null) {
        mylist2 = Printer.printList(mypol2);
    }
    List<policy> mypol3 = (List<policy>) request.getAttribute("List3");
     String mylist3 = "";
    if (mypol3 != null) {
        mylist3 = Printer.printListNSGA(mypol3);
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value='/mycss.css'/>" rel='stylesheet'>
        <title>Results</title>
    </head>
    <body>
        <h1>Results</h1>
        <%=mylist%>
        <%=mylist2%>
        <%=mylist3%>
    </body>
</html>
