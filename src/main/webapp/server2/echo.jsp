<?xml version="1.0"?>
<%@ page import="javax.servlet.http.HttpUtils,java.util.Enumeration" %>
<%@ page import="java.lang.management.*" %>
<%@ page import="java.util.*" %>
<% response.setContentType("application/xml"); %>
<Response>
<RespnseBody>This is from server 2 at <% java.util.Calendar now = Calendar.getInstance(); %>
<%= now.getTime() %> </RespnseBody>
<OriginalRequest>
	<Headers>
           <Header>
            <Name>Sender</Name>
            <!-- Server that sent this request -->
            <Value><%= request.getRemoteAddr()%></Value>
        </Header>
            
            <Header>
           <!-- getMethod() returns the name of the HTTP 
method with which this request was made,
             for example, GET, POST, or PUT -->
             <Name>HTTP Request Verb</Name>
             <Value><%= request.getMethod() %></Value>
        </Header>
        <Header>
            <Name>Request Path</Name>
            <!-- getRequestURI() returns the part of this request's URL -->
            <Value><%= request.getRequestURI() +"?"+request.getQueryString()%></Value>
        </Header>
        <%
         /*This method returns an enumeration of all the header names this 
          request contains.*/       
         java.util.Enumeration names = request.getHeaderNames();
         while (names.hasMoreElements()) {
         String hname = (String)names.nextElement();
        %>
        <Header>
            <Name> <%= hname %> </Name>
                <!-- This method returns the value of the 
specified request header as a String. -->
                <Value><%= request.getHeader(hname) %></Value>
        </Header>
   <%
        }
   %>
	</Headers>
	<Body>
	<![CDATA[
	]]>
	</Body>
</OriginalRequest>
</Response>

