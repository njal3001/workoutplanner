package core;

import java.util.ArrayList;
import java.util.Collection;

public class Workout {
	
	private String name;
	private Day day;
	private Collection<WorkoutExercise> workoutExercises = new ArrayList<>();
	
	public Workout(String name, Day day) {
		setName(name);
		setDay(day);
	}
	
	// Get total time of workout in minutes, assumes that one rep takes 5 seconds
	public int getTime() {
		int time = 0;
		for(WorkoutExercise workoutExercise : workoutExercises) {
			int sets = workoutExercise.getSets();
			int reps = workoutExercise.getReps();
			int restTime = workoutExercise.getRestTime();
			int repTime = workoutExercise.getRepTime();
			time += sets * (reps * repTime + restTime);
		}
		return time / 60;
	}
	
	public void addWorkoutExercise(WorkoutExercise workoutExercise) {
		workoutExercises.add(workoutExercise); 
	}

	public void removeWorkoutExercise(WorkoutExercise workoutExercise) {
		workoutExercises.remove(workoutExercise);
	}
	
	public Collection<WorkoutExercise> getExercises(){
		return new ArrayList<>(workoutExercises);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public static void main(String[] args) {
		Workout workout = new Workout("Chest and legs", Day.ANY);
		WorkoutExercise exercise1 = new WorkoutExercise("Bench press", "..", BodyPart.CHEST, 90, 2, 3, 5);
 		WorkoutExercise exercise2 = new WorkoutExercise("Squat", "..", BodyPart.UPPER_LEG, 90, 2, 3, 5);
		workout.addWorkoutExercise(exercise1);
		workout.addWorkoutExercise(exercise2);
 		workout.getTime();
	}
	
}
