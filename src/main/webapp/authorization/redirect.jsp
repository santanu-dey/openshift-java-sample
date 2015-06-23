<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    String redirectURL = "http://test-4g-prod.apigee.net/v2.1/oauth/redirect?response_type=code&client_id=Z4GHyW8o3JXUrkAkGetWyUYLw99uMAkC";
    response.sendRedirect(redirectURL);
%>

