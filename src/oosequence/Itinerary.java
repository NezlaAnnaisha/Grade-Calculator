package oosequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Itinerary {
	private ArrayList<TripComponent> flights;
	private String name;
	
	public Itinerary (String flightName) {
		name = flightName;
		flights = new ArrayList<TripComponent>();
	}
	
	public void addFlight(TripComponent flightsAdded) {
		
		
		// Assume max list is 10, add all flights first before modifying
		if (flights.size() < 10) {		
			flights.add(flightsAdded);		
		} 
				
		// Loop to get first flight
		for (int f1 = 0 ; f1 < flights.size() ; f1++) {
			
			// Loop to get second flight
			for (int f2 = f1+1 ; f2 < flights.size() ; f2++) {
					
				Date firstFlightA = flights.get(f1).getEnd();
				Date firstFlightD = flights.get(f1).getStart();
				Date secondFlightA = flights.get(f2).getEnd();
				Date secondFlightD = flights.get(f2).getStart();					
				
								
				// Check for the overlapping flight when more than 6 is entered		
				if (secondFlightD.after(firstFlightD) && secondFlightD.before(firstFlightA)) {
					flights.remove(f2);
				}												
				
				
				// Check if second flight's departure is before first flight's arrival
				if (secondFlightA.before(firstFlightD)) {
					Collections.swap(flights, f1, f2);
				}					
			}				
		}		
	}
	
	public long getTotalLayover() {
		
		long layoverTotal = 0;
		
		// Loop through each flight in array
		for (int i = 0 ; i < flights.size()-1 ; i++) {			
				
				Date arrival = flights.get(i).getEnd();
				Date departure = flights.get(i+1).getStart();					
					
				//lay-over should be time between first flight's arrival waiting for second flight's departure
				long layoverTime = (departure.getTime() - arrival.getTime()) / 60000;
					
				layoverTotal += layoverTime;
		}		

		return layoverTotal;
	}

	public ArrayList<TripComponent> getFlights() {
		return flights;
	}

	public String getName() {
		return name;
	}
	
}