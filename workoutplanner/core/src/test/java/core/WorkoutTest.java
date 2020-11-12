package core;

import core.BodyPart;
import core.Day;
import core.Workout;
import core.WorkoutExercise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {

    private Workout workout;
    private WorkoutExercise exercise1, exercise2;

    @BeforeEach
    public void setUp() {
        workout = new Workout("Chest and legs", Day.ANY);
        exercise1 = new WorkoutExercise("Bench press", "..", BodyPart.CHEST, 90, 2, 3, 5);
        exercise2 = new WorkoutExercise("Squat", "..", BodyPart.UPPER_LEG, 90, 2, 3, 5);
    }

    @Test
    void testGetTime() {
        workout.addWorkoutExercise(exercise1);
        workout.addWorkoutExercise(exercise2);
        assertEquals(7, workout.getTime());
    }
}
