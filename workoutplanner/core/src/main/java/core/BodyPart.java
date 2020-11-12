package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class BodyPart {

	private String name;

	public static BodyPart CARDIO = new BodyPart("Cardio"), BACK = new BodyPart("Back"),
			CHEST = new BodyPart("Chest"), BICEPS = new BodyPart("Biceps"),
			TRICEPS = new BodyPart("Triceps"), GLUTES = new BodyPart("Glutes"),
			FOREARM = new BodyPart("Forearm"), ABS = new BodyPart("Abs"),
			SHOULDERS = new BodyPart("Shoulders"), UPPER_LEG = new BodyPart("Upper Leg"),
			LOWER_LEG = new BodyPart("Lower Leg");

	private BodyPart(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public static Collection<BodyPart> getAllBodyParts(){
		return new ArrayList<BodyPart>(Arrays.asList(CARDIO, BACK,
				CHEST, BICEPS, TRICEPS, GLUTES, FOREARM, ABS, SHOULDERS, UPPER_LEG, LOWER_LEG));
	}

	public static BodyPart valueOf(String name) {
		return getAllBodyParts().stream().filter(e -> e.name.equals(name)).findFirst().orElse(null);
	}
}
