package ticketing;

public class ArrivalTimeCalculator {

	private Customer customer;
	private int maxIndexCount = 6;
	
	private int[][] distanceMatrix = {{   0,   16,   22, 1000,   20,   29, 1000}, 
									  {  16,    0,   28,   31, 1000, 1000, 1000},
									  {  22,   28,    0,   32, 1000,   23, 1000},
									  {1000,   31,   32,    0, 1000,   15,   18},
									  {  20, 1000, 1000, 1000,    0,   35,   25},
									  {  29, 1000,   23,   15,   35,    0,   23},
									  {1000, 1000, 1000,   18,   25,   12,    0}};
										
	public ArrivalTimeCalculator(Customer customer) {
		this.customer = customer;
	}

	public void calculate() {
		
		int arrivalTime = 0;
		int boardingTime = customer.getBoardingTime();
		int travelTime = getTravelTime(customer.getDeparture(), customer.getDestination());
		
		arrivalTime = boardingTime + travelTime;
		
		customer.setArrivalTimeAtDestination(arrivalTime);
	}

	private int getTravelTime(String departure, String destination) {
		int travelTime = 0;
		int departureIndex = getIndex(departure);
		int destinationIndex = getIndex(destination);
		
		for(int v = 0; v < maxIndexCount + 1; v++) {  //중간노드
			  for(int r = 0; r < maxIndexCount + 1; r++) {   //시작노드
			   for(int s = 0; s < maxIndexCount + 1; s++) { //도착노드
			    if (distanceMatrix[r][v] + distanceMatrix[v][s] < distanceMatrix[r][s]) {
			    	distanceMatrix[r][s] = distanceMatrix[r][v] + distanceMatrix[v][s];
			    }
			   }
			  }
			 }
		
		travelTime = distanceMatrix[departureIndex][destinationIndex];
		return travelTime;
	}

	private int getIndex(String name) {
		int index = 0;
		switch(name) {
		case "Seoul" :
			index = 0;
			break;
		case "Chuncheon" :
			index = 1;
			break;
		case "Wonju" :
			index = 2;
			break;
		case "Kyungju" :
			index = 3;
			break;
		case "Asan" :
			index = 4;
			break;
		case "Deajeon" :
			index = 5;
			break;
		case "Gwangju" :
			index = 6;
			break;
		}
		return index;
	}

}
