package oosequence;

public class Flight extends TripComponent {
	private String departureAirport;
	private String arrivalAirport;

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {

		// Check if airport code is null
		if (departureAirport != null) {

			// Check if airport code is length 3
			if (departureAirport.length() == 3) {

				// Check if each code is a letter
				for (char c : departureAirport.toCharArray()) {
					if (!Character.isLetter(c)) {
						this.departureAirport = "";
					}	
				}			

				this.departureAirport = departureAirport;

			} else {
				this.departureAirport = "";
			}

		} else {
			this.departureAirport = "";
		}

	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {

		// Check if airport code is null
		if (arrivalAirport != null) {

			// Check if airport code is length 3
			if (arrivalAirport.length() == 3) {

				// Check if each code is a letter
				for (char c : arrivalAirport.toCharArray()) {
					if (!Character.isLetter(c)) {
						this.arrivalAirport = "";
					}	
				}			

				this.arrivalAirport = arrivalAirport;

			} else {
				this.arrivalAirport = "";
			}

		} else {
			this.arrivalAirport = "";
		}

	}

	public String getDuration() {
		return "";
	}


}