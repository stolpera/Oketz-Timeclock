package okets.com.timeclock.model;

import java.sql.Time;
import java.sql.Date;

public class Record {

	
	private boolean didCheckIn;
	private Time time;
	private Employee employee;
	private Date date;

	public String toString()
	{
		String checkin = didCheckIn ? "IN" : "OUT";
		return employee.id + " " + date.toString() + " " + time.toString() + " " + checkin ;
	}
	
}
