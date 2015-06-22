package training;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class CustomerInfoWebService {
	
	@WebMethod()
	public Customer HelloWorld(String name){
		Customer c1 = new Customer();
		c1.setId("12345");
		c1.setFirstName("John");
		c1.setLastName("Doe");
		c1.setCountry("United States");
		c1.setEmail("john.doe@mailgroup.net");
		c1.setPhone("+1-650-637-1923");
		return c1;
	}

}
