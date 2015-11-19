package com.wsg.util;

import java.io.IOException;
import java.io.PrintWriter;
import org.json.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConvertPayload
 */
@WebServlet("/ConvertPayload")
public class ConvertPayload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConvertPayload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseBody = 
				"<!DOCTYPE html>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.0/css/bootstrap.min.css\"/>\r\n<html>\r\n\r\n<head>\r\n<style type=\"text/css\" media=\"screen\">\r\n    #editor { \r\n/*        position: absolute;\r\n        top: 0;\r\n        right: 0;\r\n        bottom: 0;\r\n        left: 0;*/\r\n        min-height: 400px;\r\n        height:auto;\r\n    }\r\n</style>\r\n\r\n</head>\r\n\r\n<body>\r\n\r\n\r\n<!-- <div id=\"editor\">\r\n<p><doc>paste your xml here</doc></p>\r\n</div>\r\n -->\r\n\r\n <div class=\"container\">\r\n    <div class=\"panel panel-default\">\r\n        <div class=\"panel-heading\">\r\n             <span class=\"panel-title\">Code Converter</span>\r\n        </div>\r\n        <div class=\"panel-body\">\r\n            <div class=\"row\">                \r\n                <div class=\"col-md-10\">\r\n                    <div id=\"editor\">\r\n                    <p><doc>Paste your xml here</doc></p>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n    \r\n    <div class=\"text-center\"></div>\r\n    \r\n<form id=\"codeform\"  method=\"post\" action=\"convert-payload\">\r\n<input type=\"hidden\" name=\"code\" id=\"code\" value=\"\" />\r\n<input type=\"submit\" value=\"Convert\" onclick=\"getVal();\">\r\n</form>\r\n    <br/>\r\n    <br/>\r\n</div> \r\n\r\n<script src=\"https://ace.c9.io/build/src/ace.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\r\n<script type=\"text/javascript\">\r\n   var editor = ace.edit(\"editor\");\r\n   // editor.setTheme(\"ace/theme/twilight\");\r\n   editor.setTheme(\"ace/theme/monokai\");\r\n   editor.getSession().setMode(\"ace/mode/xml\");\r\n\r\n\r\n    function getVal()\r\n    {\r\n      var editor= ace.edit(\"editor\"); \r\n      var input = document.getElementById('code');\r\n      // this line is necessary\r\n      // \"editor\" is the id of the ACE editor div\r\n      input.value =editor.getSession().getValue();\r\n      return true;\r\n\r\n    }\r\n</script>\r\n\r\n</body>\r\n</html>";
				PrintWriter out = response.getWriter();
		out.print(responseBody);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseBody =  request.getParameter("code");
		String soapmessageString = 	request.getParameter("code");
		JSONObject soapDatainJsonObject = new JSONObject();
		String stringXML ="";
		try {
			soapDatainJsonObject = XML.toJSONObject(soapmessageString);
			stringXML = XML.toString(soapDatainJsonObject);
		}catch (Exception e){
			
		}
		PrintWriter out = response.getWriter();
		out.print(soapDatainJsonObject.toString(4) +"\n\n"+stringXML);
		out.flush();
	}

}
