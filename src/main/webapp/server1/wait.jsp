<?xml version="1.0"?>
<%@ page import="javax.servlet.http.HttpUtils,java.util.Enumeration" %>
<%@ page import="java.lang.management.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%
Thread.sleep(10000); // sleep 5 seconds
%>
<% response.setContentType("application/xml"); %>
<Response>
<RespnseBody>This is from server 1 at <% java.util.Calendar now = Calendar.getInstance(); %><%= now.getTime() %> </RespnseBody>
<OriginalRequest>
	<Headers>
           <Header>
            <Name>Sender</Name>
            <!-- Server that sent this request -->
            <Value><%= request.getRemoteAddr()%></Value>
        </Header>
            
            <Header>
          
             <Name>HTTP Request Verb</Name>
             <Value><%= request.getMethod() %></Value>
        </Header>
        <Header>
            <Name>Request Path</Name>
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
               
                <Value><%= request.getHeader(hname) %></Value>
        </Header>
   <%
        }
   %>
	</Headers>
	<%	StringBuilder stringBuilder = new StringBuilder();
	BufferedReader bufferedReader = null;
	try {
	  InputStream inputStream = request.getInputStream();
	  if (inputStream != null) {
	   bufferedReader = new BufferedReader(new InputStreamReader(
	inputStream));
	   char[] charBuffer = new char[128];
	   int bytesRead = -1;
	   while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	    stringBuilder.append(charBuffer, 0, bytesRead);
	   }
	  } else {
	   stringBuilder.append("");
	  }
	} catch (IOException ex) {
	  throw ex;
	} finally {
	  if (bufferedReader != null) {
	   try {
	    bufferedReader.close();
	   } catch (IOException ex) {
	    throw ex;
	   }
	  }
	}
	String body = stringBuilder.toString(); %>
	<Body>
	<![CDATA[<%=body%>
	]]>
	</Body>
</OriginalRequest>
</Response>

