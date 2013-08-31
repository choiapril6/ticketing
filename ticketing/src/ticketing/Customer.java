package ticketing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Customer {

	private int id;
	private String name;
	private int arrivalTimeAtTicketingOffice;
	private int timeForTicketing;
	private String departure;
	private String destination;
	private int ticketingStartTime = -1;
	private int ticketingEndTime = -1;
	private int boardingTime;
	private int arrivalTimeAtDestination;
	private int totalWaitingTime;
	private int waitingTimeForTicketing;
	private int waitingTimeForBoarding;
	private int departingTime;
	private static String FILE_PATH = "customers.txt";

	public Customer() {
	}

	public Customer(String line) {
		String[] tokens = line.split("\\s+");

		this.id = Integer.parseInt(tokens[0]);
		this.name = tokens[1];
		this.arrivalTimeAtTicketingOffice = Integer.parseInt(tokens[2]);
		this.timeForTicketing = Integer.parseInt(tokens[3]);
		this.departure = tokens[4];
		this.destination = tokens[5];
	}

	public static ArrayList<Customer> readCustomerListFromFile()
			throws IOException {

		ArrayList<Customer> customerList = new ArrayList<Customer>();
		FileReader fr = new FileReader(FILE_PATH);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		while ((line = br.readLine()) != null) {
			Customer customer = new Customer(line);
			customerList.add(customer);
		}

		return customerList;
	}

	public String getName() {
		return name;
	}

	
	public int getId() {
		return id;
	}

	public int getArrivalTimeAtTicketingOffice() {
		return arrivalTimeAtTicketingOffice;
	}

	//ticketing에 걸리는 시간
	public int getTimeForTicketing() {
		return timeForTicketing;
	}


	public void setTicketingStartEndTime(int time) {
		this.ticketingStartTime = time;
		this.ticketingEndTime = ticketingStartTime + timeForTicketing;
	}

	public int getTicketingEndTime() {
		return this.ticketingEndTime;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public int getTicketingStartTime() {
		return this.ticketingStartTime;
	}

	public int getBoardingTime() {
		return boardingTime;
	}
	
	public void setBoardingTime(int currentTime) {
		this.boardingTime = currentTime;
	}

	public String getDeparture() {
		return departure;
	}

	public String getDestination() {
		return destination;
	}

	public int getTotalWaitingTime() {
		return totalWaitingTime;
	}

	public void setTotalWaitingTime(int boardingTime) {
		this.totalWaitingTime = boardingTime - arrivalTimeAtTicketingOffice;
		
	}

	public void setArrivalTimeAtDestination(int arrivalTimeAtDestination) {
		this.arrivalTimeAtDestination = arrivalTimeAtDestination;
	}
	
	public int getArrivalTimeAtDestination() {
		return arrivalTimeAtDestination;
	}
	
	
	public int getWaitingTimeForTicketing() {
		return ticketingStartTime - arrivalTimeAtTicketingOffice;
	}

	public int getWaitingTimeForBoarding() {
		return boardingTime - ticketingEndTime;
	}

	public int getDepartingTime() {
		return departingTime;
	}


	public void setDepartingTime(int currentTime) {
		this.departingTime = currentTime;
		
	}

	public static Comparator<Customer> COMPARE_BY_TICKETING_TIME = new Comparator<Customer>() {
        public int compare(Customer one, Customer other) {
            return one.timeForTicketing - other.timeForTicketing;
        }
    };
    
    public static Comparator<Customer> COMPARE_BY_ID = new Comparator<Customer>() {
        public int compare(Customer one, Customer other) {
            return one.id - other.id;
        }
    };
    

	
	
}
