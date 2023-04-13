package oosequence;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Calendar;

import org.junit.Test;

public class TripComponentTest {
		
	private Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, hour, minute, 0);
		return cal.getTime();
	}
	
	// Testing constructors
	
	@Test
	public void testDefaultConstructor() {
		// This test assumes that the constructor can finish within 10 milliseconds of being called.
		TripComponent c = new TripComponent();
		Date now = new Date();
		Date actualStart = c.getStart();
		Date actualEnd = c.getEnd();
		
		Date expectedend = new Date(now.getTime() + 3600000);
		
		// testing that default constructor 'now' is + or - 10 millisecond from time of this verification.
		assertTrue("Expected start time to be now.", 
				actualStart.getTime() - 10 <= now.getTime() && now.getTime() <= actualStart.getTime() + 10);
		assertTrue("Expected end time to be one hour from now.", 
				actualEnd.getTime() - 10 <= expectedend.getTime() && expectedend.getTime() <= actualEnd.getTime() + 10);
	}

	@Test
	public void testConstructor_startBeforeend(){
		TripComponent c = new TripComponent(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		Date expectedstart = getDate(2018,11,28,10,20);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("start one minute before end, testing start", expectedstart, c.getStart());
		assertEquals("start one minute before end, testing end", expectedend, c.getEnd());
	}

	@Test
	public void testConstructor_startSameAsend(){
		TripComponent c = new TripComponent(getDate(2018,11,27,10,20), getDate(2018,11,27,10,20));
		Date expectedstart = getDate(2018,11,27,10,20);
		Date expectedend = null;
		assertEquals("testing start", expectedstart, c.getStart());
		assertEquals("testing end (which is null, expecting empty string", expectedend, c.getEnd());
	}

	@Test
	public void testConstructor_startAfterend(){
		TripComponent c = new TripComponent(getDate(2018,11,28,10,21), getDate(2018,11,28,10,20));
		Date expectedstart = getDate(2018,11,28,10,21);
		Date expectedend = null;
		assertEquals("start one minute after end, testing start", expectedstart, c.getStart());
		assertEquals("start one minute after end, testing end", expectedend, c.getEnd());
	}

	@Test
	public void testConstructor_nullstartAndend(){
		TripComponent c = new TripComponent(null,null);
		Date expectedstart = null;
		Date expectedend = null;
		assertEquals("testing start", expectedstart, c.getStart());
		assertEquals("testing end", expectedend, c.getEnd());
	}

	@Test
	public void testCopyConstructor(){
		TripComponent c = new TripComponent(getDate(2018,10,28,10,20), getDate(2018,10,29,1,5));
		TripComponent copy = new TripComponent(c);
		Date expectedstart = getDate(2018,10,28,10,20);
		Date expectedend = getDate(2018,10,29,1,5);
		assertEquals("Testing start", expectedstart, copy.getStart());
		assertEquals("Testing end", expectedend, copy.getEnd());
	}

	@Test
	public void testCopyConstructor_nullstartAndend(){
		TripComponent p = new TripComponent(null,null);
		TripComponent p2 = new TripComponent(p);
		assertEquals("testing start", null, p2.getStart());
		assertEquals("testing end", null, p2.getEnd());
	}

	// Testing setter and getters

	@Test
	public void test_setter_and_getter_start_NewstartBeforeend(){
		 
		TripComponent c = new TripComponent(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setStart(getDate(2018,11,28,8,0));
		Date expectedstart = getDate(2018,11,28,8,0);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("Changed start to 8am from 10:20am, testing start", expectedstart, c.getStart());
		assertEquals("Changed start to 8am from 10:20am, testing end", expectedend, c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_start_fromNullstart(){
		 
		TripComponent c = new TripComponent(null, getDate(2018,11,28,10,21));
		c.setStart(getDate(2018,11,28,8,0));
		Date expectedstart = getDate(2018,11,28,8,0);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("Changed start from null, testing start", expectedstart, c.getStart());
		assertEquals("Changed start from null, testing end", expectedend, c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_start_toLaterThanend(){
		 
		TripComponent c = new TripComponent(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setStart(getDate(2018,11,29,8,0));
		Date expectedstart = getDate(2018,11,28,10,20);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("Changed start to later than end, expected unchanged start, testing start", expectedstart, c.getStart());
		assertEquals("Changed start to later than end, testing end", expectedend, c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_start_Nullend(){
		 
		TripComponent c = new TripComponent(getDate(2018,11,28,10,20), null);
		c.setStart(getDate(2018,11,28,8,0));
		Date expectedstart = getDate(2018,11,28,8,0);
		Date expectedend = null;
		assertEquals("Changed start to 8am from 10:20am, testing start", expectedstart, c.getStart());
		assertEquals("Changed start to 8am from 10:20am, testing end", expectedend, c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_end_NewendAfterstart(){
		 
		TripComponent c = new TripComponent(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setEnd(getDate(2018,11,29,8,0));
		Date expectedstart = getDate(2018,11,28,10,20);
		Date expectedend = getDate(2018,11,29,8,0);
		assertEquals("Changed end to 8am on the 29th from 10:21am on the 28th, testing start", expectedstart, c.getStart());
		assertEquals("Changed end to 8am on the 29th from 10:21am on the 28th", expectedend, c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_end_fromNullend(){
		 
		TripComponent c = new TripComponent(getDate(2018,11,28,10,21), null);
		c.setEnd(getDate(2018,11,29,8,0));
		Date expectedstart = getDate(2018,11,28,10,21);
		Date expectedend = getDate(2018,11,29,8,0);
		assertEquals("Changed end from null, testing start", expectedstart, c.getStart());
		assertEquals("Changed end from null, testing end", expectedend, c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_end_toEarlierThanstart(){
		 
		TripComponent c = new TripComponent(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setEnd(getDate(2018,11,27,8,0));
		Date expectedstart = getDate(2018,11,28,10,20);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("Changed start to later than end, testing start", expectedstart, c.getStart());
		assertEquals("Changed start to later than end, expected unchanged end, testing end", expectedend, c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_end_Nullstart(){
		 
		TripComponent c = new TripComponent(null, getDate(2018,11,28,10,20));
		c.setEnd(getDate(2018,11,28,8,0));
		Date expectedend = getDate(2018,11,28,8,0);
		assertEquals("Changed end to 8am from 10:20am, testing end", expectedend, c.getEnd());
		assertEquals("Changed end to 8am from 10:20am, testing start", null, c.getStart());			
	}

	@Test
	public void test_length_startOneMinuteBeforeend() {
		 
		TripComponent c = new TripComponent(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		long expectedLength = 60;
		assertEquals("TripComponent is one minute long", expectedLength, c.lengthInSeconds());

	}

	@Test
	public void test_length_startOneHourBeforeend() {
		 
		TripComponent c = new TripComponent(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
		long expectedLength = 3600;
		assertEquals("TripComponent is one hour long", expectedLength,c.lengthInSeconds());

	}

	@Test
	public void test_length_startNull() {
		 
		TripComponent c = new TripComponent(null, getDate(2018,11,28,11,20));
		long expectedLength = 0;
		assertEquals("Null start", expectedLength,c.lengthInSeconds());

	}

	@Test
	public void test_length_endNull() {
		 
		TripComponent c = new TripComponent(getDate(2018,11,28,11,20), null);
		long expectedLength = 0;
		assertEquals("Null end", expectedLength,c.lengthInSeconds());

	}
}