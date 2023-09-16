package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class CalendarItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int day;
	private int month;
	private int year;
	private String event;
	
	public CalendarItem() {
	}
	
	public CalendarItem(int month, int day, int year, String event) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.event = event;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String returnEventDetails() {
		return this.event + ": " + this.month + "/" + this.day + "/" + this.year;
	}
	
}
