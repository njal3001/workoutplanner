package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ExerciseList implements Iterable<Exercise>{
	
	private Collection<Exercise> exercises = new HashSet<>();

	public boolean addExercise(Collection<Exercise> exercises){
        boolean changed = this.exercises.addAll(exercises);
		if(changed)
            fireExerciseListChanged();
        return changed;
	}

	public boolean addExercise(Exercise exercise) {
        boolean changed = exercises.add(exercise);
		if(changed)
            fireExerciseListChanged();
        return changed;
	}

	public boolean removeExercise(Exercise exercise) {
		boolean changed = exercises.remove(exercise);
        if(changed)
            fireExerciseListChanged();
        return changed;
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
