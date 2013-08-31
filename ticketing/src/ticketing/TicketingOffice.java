package ticketing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TicketingOffice {

	private static final int MAX_TIME = 100;
	private static ArrayList<Agent> agents = new ArrayList<Agent>();
	private ArrayList<Customer> waitingAgentLine = new ArrayList<Customer>();
	private ArrayList<Train> trainList = new ArrayList<Train>();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private ArrayList<Customer> waitingTrainLine = new ArrayList<Customer>();
	private ArrayList<Customer> remainingCustomer = new ArrayList<Customer>();
	private TravelReport report = new TravelReport();

	public TicketingOffice() {
		setUpAgents();
	}

	private void setUpAgents() {
		for (int i = 0; i < 3; i++) {
			agents.add(new Agent());
		}
	}

	private void setCustomerList(ArrayList<Customer> list) {
		this.customerList = list;
		this.remainingCustomer = list;
	}

	public void ticketing(ArrayList<Customer> list) throws IOException {

		setCustomerList(list);

		int currentTime;
		for (currentTime = 0; currentTime < MAX_TIME; currentTime++) {
			if (remainingCustomer.size() == 0 && isEveryAgentDone() == true 
					&& waitingTrainLine.size() == 0) {
				break;
			}
			lineUpWaitingQueue(currentTime);
			allocateCustomerToAgent(currentTime);
			ticketingByAgent(currentTime);
			if(currentTime !=0 && currentTime % 5 == 0) {
				startTrain(currentTime);
			}
		}
		report.documentCustomer(trainList);
	}



	private void lineUpWaitingQueue(int currentTime) {
		for (Customer c : customerList) {
			if (currentTime == c.getArrivalTimeAtTicketingOffice()) {
				waitingAgentLine.add(c);
			}
		}
		Collections.sort(waitingAgentLine, Customer.COMPARE_BY_TICKETING_TIME);
	}

	private void allocateCustomerToAgent(int time) {

		Iterator<Customer> i = waitingAgentLine.iterator();
		while (i.hasNext()) {
			Customer nextCustomer = i.next();
			Agent a = getLeastBusyAgent();
			a.enqueueCustomer(nextCustomer);
		}
		waitingAgentLine.clear();
	}

	
	private void ticketingByAgent(int currentTime) {
		for (Agent a : agents) {
			Customer finishedCustomer = a.ticketing(currentTime);
			if (finishedCustomer == null) {
				
			} else {
				waitingTrainLine.add(finishedCustomer);
				System.out.println(finishedCustomer + " done*");
				remainingCustomer.remove(finishedCustomer);
				a.dequeueCustomer();
			}
		}
	}


	private void startTrain(int currentTime) throws IOException {
		Train t = new Train(currentTime / 5);
		trainList.add(t);
		System.out.println("\t" + t);
		for(Customer c: waitingTrainLine) {
			System.out.println("\t" + c);
			t.boardCustomer(c, currentTime);
		}
		waitingTrainLine.clear();
	}




	private boolean isEveryAgentDone() {
		if (agents.get(0).getTimeNeededToFinish() == 0
				&& agents.get(1).getTimeNeededToFinish() == 0
				&& agents.get(2).getTimeNeededToFinish() == 0)
			return true;
		else
			return false;
	}


	private Agent getLeastBusyAgent() {
		Agent leastBusyAgent = agents.get(0);
		for (Agent a : agents) {
			if (leastBusyAgent.getTimeNeededToFinish() >= a
					.getTimeNeededToFinish())
				leastBusyAgent = a;
			else
				continue;
		}
		return leastBusyAgent;
	}
}
