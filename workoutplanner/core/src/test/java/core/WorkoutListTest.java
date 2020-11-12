package core;

import core.Day;
import core.Workout;
import core.WorkoutList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WorkoutListTest {

    private WorkoutList workoutList;
    private Workout workout1, workout2, workout3;

    @BeforeEach
    public void setUp(){
        workoutList = new WorkoutList();
        workout1 = new Workout("1", Day.MONDAY);
        workout2 = new Workout("2", Day.THURSDAY);
        workout3 = new Workout("3", Day.ANY);
    }

    @Test
    public void testAddWorkout_Sorted(){
        workoutList.addWorkout(workout3);
        workoutList.addWorkout(workout2);
        workoutList.addWorkout(workout1);
        List<Workout> expected = new ArrayList<Workout>(Arrays.asList(workout1, workout2, workout3));
        assertEquals(expected, workoutList.getWorkouts());
    }


}
