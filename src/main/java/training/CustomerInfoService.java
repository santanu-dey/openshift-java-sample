package training;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerInfoService
 */
@WebServlet("/CustomerInfoService")
public class CustomerInfoService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerInfoService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	//
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		String responseBody = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "\r\n    <soap:Body>\r\n        <ns2:CustomerInfoServiceResponse "
				+ "\r\n            xmlns:ns2=\"http://training/\">"
				+ "\r\n            <return>"
				+ "\r\n                <country>United States</country>"
				+ "\r\n                <email>john.doe@mailgroup.net</email>"
				+ "\r\n                <firstName>John</firstName>"
				+ "\r\n                <id>12345</id>"
				+ "\r\n                <lastName>Doe</lastName>"
				+ "\r\n                <phone>+1-650-637-1923</phone>"
				+ "\r\n            </return>"
				+ "\r\n        </ns2:CustomerInfoServiceResponse>"
				+ "\r\n    </soap:Body>"
				+ "\r\n</soap:Envelope>";
		
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(responseBody);
		out.flush();
	}

}
