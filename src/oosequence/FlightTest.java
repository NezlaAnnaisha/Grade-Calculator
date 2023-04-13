package oosequence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class FlightTest {
	private Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, hour, minute, 0);
		return cal.getTime();
	}
		
	@Test
	public void test_getter_setter_arrivalAirport_validAirport(){
		

		Flight c = new Flight();
		c.setArrivalAirport("BAC");
		assertEquals("Set arrival airport to BAC", "BAC", c.getArrivalAirport());
	}

	@Test
	public void test_getter_setter_departureAirport_Invalid_TooShort(){
		

		Flight c = new Flight();
		c.setDepartureAirport("YY");
		assertEquals("Set departure airport to YY", "", c.getDepartureAirport());
	}
	
	@Test
	public void test_getter_setter_setTwice() {
		Flight c = new Flight();
		c.setDepartureAirport("YYY");
		c.setDepartureAirport("WXYZ");
		assertEquals("Changed departure airport from YYY to WXYZ", "", c.getDepartureAirport());
	}
	
	
	@Test
	public void test_getter_setter_arrivalAirport_Invalid_TooLong(){
		Flight c = new Flight();
		c.setArrivalAirport("BABA");
		assertEquals("Set arrival airport to BABA", "", c.getArrivalAirport());
	}
	
	@Test
	public void test_getter_setter_departureAirport_Invalid_null(){
		Flight c = new Flight();
		c.setDepartureAirport(null);
		assertEquals("Set departure airport to null", "", c.getDepartureAirport());
	}
	
	@Test
	public void test_getter_setter_arrivalAirport_Invalid_null(){
		Flight c = new Flight();
		c.setArrivalAirport(null);
		assertEquals("Set arrival airport to null", "", c.getArrivalAirport());
	}
	
	@Test
	public void test_getDuration_startOneHourAndTenMinutesBeforeEnd() {
		
		Flight c = new Flight();
		c.setStart(getDate(2018,11,28,10,20));
		c.setEnd(getDate(2018,11,28,11,30));
		String expectedDuration = "70 minutes";
		assertEquals("Flight is one hour and 10 minutes long", expectedDuration, c.getDuration());		
	}
}