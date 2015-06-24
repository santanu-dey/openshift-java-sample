<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    String redirectURL = request.getParameter("redirect_uri");
	if (redirectURL==null)
	{
		redirectURL="http://localhost";
	}
	String basicScope = request.getParameter("basic");
	String extendedScope = request.getParameter("extended");
	String scope="";
	if (basicScope!=null && basicScope.toLowerCase().equals("true")){
		scope="basic";
	}
	if (extendedScope!=null && extendedScope.toLowerCase().equals("true")){
		scope="basic%20extended";
	}
	redirectURL=redirectURL+"&scope="+scope;
    response.sendRedirect(redirectURL);
%>

