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

	}


	public long length() {		
		return 0;
	}





}

