package core;

import java.util.ArrayList;
import java.util.List;

public class WorkoutList {
	
	private List<Workout> workoutList = new ArrayList<>();
	
	public void addWorkout(Workout workout) {
		workoutList.add(workout);
		sort();
	}

	public void removeWorkout(Workout workout) {
		workoutList.remove(workout);
	}
	
	public List<Workout> getWorkouts(){
		return new ArrayList<>(workoutList);
	}
	
	private void sort() {
		workoutList.sort((o1, o2) -> o1.getDay().compareTo(o2.getDay()));
	}
}
