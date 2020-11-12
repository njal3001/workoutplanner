package ui;

import core.BodyPart;
import core.Equipment;
import core.Exercise;
import core.ExerciseList;
import json.WorkoutPlannerPersistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collection;

public class DirectWorkoutPlannerAccess implements WorkoutPlannerAccess {

    private final ExerciseList exerciseList;
    private final WorkoutPlannerPersistence workoutPlannerPersistence;

    public DirectWorkoutPlannerAccess(ExerciseList exerciseList){
        this.exerciseList = exerciseList;
        this.workoutPlannerPersistence = new WorkoutPlannerPersistence();
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
