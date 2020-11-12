package ui;

import core.*;

import java.util.Collection;

public interface WorkoutPlannerAccess {

    Collection<Exercise> getExercises();

    void removeExercise(Exercise exercise);

    void addExercise(Exercise exercise);

    Collection<Equipment> getAllEquipment();

    Collection<BodyPart> getAllBodyParts();

}
