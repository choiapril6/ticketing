package ticketing;

import java.io.IOException;
import java.util.ArrayList;


public class TicketingMain {
	
	public static void main(String[] args) throws IOException {

		ArrayList<Customer> list = Customer.readCustomerListFromFile();
		TicketingOffice t = new TicketingOffice();
		t.ticketing(list);
	}
}
