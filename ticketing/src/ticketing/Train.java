package ticketing;

import java.util.ArrayList;

public class Train {

	private int number;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public Train(int number) {
		this.number = number;
	}

	public void boardCustomer(Customer customer, int currentTime) {
		customers.add(customer);
		setTimeForCustomer(customer, currentTime);
		
	}
	
	
	private void setTimeForCustomer(Customer customer, int currentTime) {

		customer.setBoardingTime(currentTime);
		customer.setTotalWaitingTime(currentTime);
		ArrivalTimeCalculator atc = new ArrivalTimeCalculator(customer);
		atc.calculate();		
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public int getNumber() {
		return number;
	}
	
	
	
}
