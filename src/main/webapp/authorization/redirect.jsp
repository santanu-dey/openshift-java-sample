<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    String redirectURL = request.getParameter("redirect_uri");
    response.sendRedirect(redirectURL);
%>

