package oosequence;

import java.util.Date;

public class Flight {
	Date departure;
	Date arrival;


	public Flight(Date departureTime, Date arrivalTime){
		departure = departureTime;
		arrival = arrivalTime;

		// When either one or both arguments are null, set to instance variables directly
		if (departureTime == null || arrivalTime == null) {
			departure = departureTime;
			arrival = arrivalTime;
		}

		// When neither arguments are null, check if departure is before arrival time
		if (departureTime != null && arrivalTime != null){

			// Set departure and arrival to null if departure comes before arrival time
			if (departureTime.before(arrivalTime) == false) {
				departure = null;
				arrival = null;
			}						
		}
	}


	public Flight(Flight copy){
		
		// No validation needed and make direct copy to departure and arrival times
		departure = copy.departure;
		arrival = copy.arrival;

	}


	public long length() {	
		
		// If departure or arrival times are null, set length to zero as default
		if(departure == null || arrival == null) {
			return 0;
		} else {
			
			// Divide by 60000 to convert from milliseconds to minutes
			long flightDuration = (arrival.getTime() - departure.getTime()) / 60000;
			return flightDuration;
			
		}
		
	}





}

