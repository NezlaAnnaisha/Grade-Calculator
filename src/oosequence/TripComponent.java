package oosequence;

import java.util.Date;

public class TripComponent {
	private Date start;
	private Date end;
	
	public TripComponent() {
		Date current = new Date();
		start = current;
		
		// Set end to one hour after start time
		long dateToAdd = current.getTime();	
		Date oneHourAfterStart = new Date(dateToAdd + 3600000);	
		end = oneHourAfterStart;
	}


	public TripComponent(Date startDate, Date endDate){
		setStart(startDate);
		setEnd(endDate);

		// When either one or both arguments are null, set to instance variables directly
		if (startDate == null || endDate == null) {
			setStart(startDate);
			setEnd(endDate);
		}

		// When neither arguments are null, check if departure is before arrival time
		if (startDate != null && endDate != null){
			
			// Check if start is the same as end
			if(startDate.equals(endDate)) {
				setEnd(null);
			}
			
			// Check if start is after end
			else if (startDate.after(endDate)) {
				setEnd(null);
			}

			// Set departure and arrival to null if departure comes before arrival time
			else if (startDate.before(endDate) == false) {
				setStart(null);
				setEnd(null);
			}						
		}
	}


	public TripComponent(TripComponent toCopy){
		
		// No validation needed and make direct copy to departure and arrival times
		setStart(toCopy.getStart());
		setEnd(toCopy.getEnd());

	}


	public long lengthInSeconds() {	
		
		// If departure or arrival times are null, set length to zero as default
		if(getStart() == null || getEnd() == null) {
			return 0;
		} else {	
			// Divide by 60000 to convert from milliseconds to seconds
			long lengthDuration = (getEnd().getTime() - getStart().getTime()) / 1000;
			return lengthDuration;	
		}
		
	}


	public Date getStart() {		
		return start;
	}


	public Date getEnd() {		
		return end;
	}


	public void setStart(Date startDate) {
		
		// New departure time should only replace original departure IF departure is before arrival
		// Check if variables are nulls
		if (this.start != null && this.end != null) {		
					
			// Check if departure is before arrival
			if (this.start.before(end)) { 
						
				if (startDate.before(this.start)) {
					this.start = startDate;
				} else {
					startDate = this.start;
				}
						
			} else {					
				this.start = startDate;
			}
						
		} 
					
		this.start = startDate;
			
	}


	public void setEnd(Date endDate) {

		// New arrival time should only replace original arrival IF arrival is after departure
		// Check if variables are nulls
		if (this.end != null && this.start != null) {					
			
			// Check if arrival is after departure
			if (this.end.after(start)) { 				
				
				if (endDate.after(this.end)) {
					this.end = endDate;
				} else {
					endDate = this.end;
				}
				
			} else {				
				this.end = endDate;
			}
								
		} 		
		
		this.end = endDate;	

	}





}

