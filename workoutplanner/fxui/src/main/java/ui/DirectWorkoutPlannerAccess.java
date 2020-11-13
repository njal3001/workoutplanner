package ui;

import core.BodyPart;
import core.Equipment;
import core.Exercise;
import core.ExerciseList;


import java.util.Collection;

public class DirectWorkoutPlannerAccess implements WorkoutPlannerAccess {

    private final ExerciseList exerciseList;

    public DirectWorkoutPlannerAccess(ExerciseList exerciseList){
        this.exerciseList = exerciseList;
    }

    @Override
    public Collection<Exercise> getExercises() {
        return exerciseList.getExercises();
    }


    @Override
    public void removeExercise(Exercise exercise) {
        exerciseList.removeExercise(exercise);
    }

    @Override
    public void addExercise(Exercise exercise) {
        exerciseList.addExercise(exercise);
    }

    @Override
    public Collection<Equipment> getAllEquipment() {
        return Equipment.getAllEquipment();
    }

    @Override
    public Collection<BodyPart> getAllBodyParts() {
        return BodyPart.getAllBodyParts();
    }

}
