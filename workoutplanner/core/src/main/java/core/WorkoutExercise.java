package core;

public class WorkoutExercise extends Exercise {

	// In seconds
	private int restTime;
	private int repTime;

	private int sets;
	private int reps;
	
	public WorkoutExercise(String name, String description, BodyPart bodyPart, int restTime, int sets, int reps, int repTime) {
		super(name, description, bodyPart);
		setRestTime(restTime);
		setSets(sets);
		setReps(reps);
		setRepTime(repTime);
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		if(restTime < 0)
			throw new IllegalArgumentException("Rest time can't be negative");
		this.restTime = restTime;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		if(sets <= 0)
			throw new IllegalArgumentException("Number of sets must be more than 0");
		this.sets = sets;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		if(reps <= 0)
			throw new IllegalArgumentException("Number of reps must be more than 0");
		this.reps = reps;
	}

	public int getRepTime() {
		return repTime;
	}

	public void setRepTime(int repTime) {
		if(repTime < 0)
			throw new IllegalArgumentException("Rep time can't be negative");
		this.repTime = repTime;
	}
}
