package ticketing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class TravelReport {


	public void documentCustomer(ArrayList<Train> trainList) throws IOException {
		
		ArrayList<Customer> entireCustomerList = new ArrayList<Customer>();
		
		FileWriter fw = new FileWriter("TravelReport.txt");
		
		for(Train t: trainList) {
			for(Customer c1: t.getCustomers()) {
				entireCustomerList.add(c1);
			}
		}
		Collections.sort(entireCustomerList, Customer.COMPARE_BY_ID);
		
		StringBuilder header = makeReportHeader();
		fw.write(header.toString());
		for(Customer c: entireCustomerList) {
			
			String oneCustomerInfo = String.format("|%10s |%10s |%10s |%10s |%10s |%10s\n", 
					c.getId(), c.getWaitingTimeForTicketing(), c.getTimeForTicketing(), c.getWaitingTimeForBoarding(),
					c.getDepartingTime(), c.getArrivalTimeAtDestination());
		
			fw.write(oneCustomerInfo);
		}
		
		fw.close();
	}

	
		private StringBuilder makeReportHeader() {
			StringBuilder headerBuffer = new StringBuilder();
			String firstLine = String.format("|%10s |%10s |%10s |%10s |%10s |%10s\n", 
					"customer", "waiting", "ticketing", "waiting", "train", "train");
			String secondLine = String.format("|%10s |%10s |%10s |%10s |%10s |%10s\n", 
					"id", "ticketing", "time", "train", "departs", "arrives");
			headerBuffer.append(firstLine);
			headerBuffer.append(secondLine);
			
			return headerBuffer;
		}
}



