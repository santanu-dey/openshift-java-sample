package com.wsg.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.json.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

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
		JSONObject soapDatainJsonObject = null;
		String stringXML ="";
		Document xmlDoc= null;
		try {
			soapDatainJsonObject = JSONML.toJSONObject(soapmessageString);
			stringXML = JSONML.toString(soapDatainJsonObject);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			ByteArrayInputStream stream = new ByteArrayInputStream(stringXML.getBytes());
			xmlDoc = dBuilder.parse(stream);
			xmlDoc.getDocumentElement().normalize();
			System.out.println("hello\n\n\n");
			NodeList nodeList = xmlDoc.getElementsByTagName("*");
			System.out.println("Length of nodes ="+nodeList.getLength());
		    for (int i = 0; i < nodeList.getLength(); i++) {
		        Node node = nodeList.item(i);
		        System.out.println(node.getNodeName());
		        if (node.getNodeType() == Node.ELEMENT_NODE) {
		            // do something with the current element
		            if (node.getNodeName().toLowerCase().startsWith("xmlns:"))
		            {
		            	// add attribute 
		            	Element parentElement = (Element) node.getParentNode();
		            	parentElement.setAttribute(node.getNodeName(), node.getTextContent());
		            	parentElement.removeChild(node);
		            }
		            
		        }
		    }
			
			
		}catch (Exception e){
			e.printStackTrace();
			stringXML = e.getMessage() + e.toString();
		}
		PrintWriter out = response.getWriter();
		String xmlString = "Could not convert document back to XML. Please check input error or conversion error:\n";
		if (xmlDoc!=null && soapDatainJsonObject!=null){
			try {
				xmlString= "Re-converted back into XML: \n\n "+getXMLString(xmlDoc);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				xmlString = xmlString+ e.getMessage() + e.toString();
				e.printStackTrace();
			}
		
			out.print("Converted JSON format:\n\n"+soapDatainJsonObject.toString(4)+"\n\n"+xmlString);
		}else{
			out.print("Could not convert document. Please check document format. Caused by:\n"+stringXML);
		}
		
		out.flush();
	}
	public static String getXMLString(Document doc) throws TransformerException{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		//String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
		String output = writer.getBuffer().toString();
		return output;
	}

}
