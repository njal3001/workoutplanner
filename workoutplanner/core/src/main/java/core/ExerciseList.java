package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ExerciseList implements Iterable<Exercise>{
	
	private Collection<Exercise> exercises = new HashSet<>();

	public void addExercise(Collection<Exercise> exercises){
		if(this.exercises.addAll(exercises))
			fireExerciseListChanged();
	}

	public void addExercise(Exercise exercise) {
		if(exercises.add(exercise))
			fireExerciseListChanged();
	}

	public void removeExercise(Exercise exercise) {
		if(exercises.remove(exercise))
			fireExerciseListChanged();
	}

	public void clear(){
		exercises.clear();
	}
	
	public Collection<Exercise> getExercises() {
		return new ArrayList<>(exercises);
	}

	private Collection<ExerciseListListener> exerciseListListeners = new ArrayList<>();

	public void addExerciseListListener(ExerciseListListener exerciseListListener){
		exerciseListListeners.add(exerciseListListener);
	}

	public void removeExerciseListListener(ExerciseListListener exerciseListListener){
		exerciseListListeners.remove(exerciseListListener);
	}

	protected void fireExerciseListChanged(){
		for(ExerciseListListener exerciseListListener : exerciseListListeners)
			exerciseListListener.ExerciseListChanged(this);
	}

	public Iterator<Exercise> iterator(){
		return exercises.iterator();
	}

}
