package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day implements Comparable<Day>{

	private String name;

	private Day(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public static Day MONDAY = new Day("Monday"), TUESDAY= new Day("TUESDAY"), WEDNESDAY = new Day("Wednesday"),
			THURSDAY = new Day("Thursday"), FRIDAY = new Day("Friday"), SATURDAY = new Day("Saturday"), 
			SUNDAY = new Day("Sunday"), ANY = new Day("Any");

	@Override
	public int compareTo(Day other) {
		List<Day> sorted = new ArrayList<>(Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, ANY));
		return sorted.indexOf(this) - sorted.indexOf(other);
	}

}
