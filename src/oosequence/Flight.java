package oosequence;

import java.util.Date;

public class Flight {
	private Date departure;
	private Date arrival;


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


	public Date getDeparture() {		
		return departure;
	}


	public Date getArrival() {		
		return arrival;
	}


	public void setDeparture(Date departure) {
		
		// New departure time should only replace original departure IF departure is before arrival
		// Check if variables are nulls
		if (this.departure != null && this.arrival != null) {		
					
			// Check if departure is before arrival
			if (this.departure.before(arrival)) { 
						
				if (departure.before(this.departure)) {
					this.departure = departure;
				} else {
					departure = this.departure;
				}
						
			} else {					
				this.departure = departure;
			}
						
		} 
					
		this.departure = departure;
			
	}


	public void setArrival(Date arrival) {

		// New arrival time should only replace original arrival IF arrival is after departure
		// Check if variables are nulls
		if (this.arrival != null && this.departure != null) {					
			
			// Check if arrival is after departure
			if (this.arrival.after(departure)) { 				
				
				if (arrival.after(this.arrival)) {
					this.arrival = arrival;
				} else {
					arrival = this.arrival;
				}
				
			} else {				
				this.arrival = arrival;
			}
								
		} 		
		
		this.arrival = arrival;	

	}





}

