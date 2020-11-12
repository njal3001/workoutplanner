package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class FilteredExerciseListTest {

    private FilteredExerciseList filteredExerciseList;
    private Exercise exercise1, exercise2, exercise3, exercise4, exercise5;

    @BeforeEach
    public void setUp(){
        filteredExerciseList = new FilteredExerciseList();
        exercise1 = new Exercise("Bench press", "..", BodyPart.CHEST);
        exercise1.addEquipment(Equipment.BENCH);
        exercise1.addEquipment(Equipment.BARBELL);
        exercise2 = new Exercise("Squat", "..", BodyPart.LOWER_LEG);
        exercise2.addEquipment(Equipment.BARBELL);
        exercise3 = new Exercise("Shoulder press", "..", BodyPart.SHOULDERS);
        exercise3.addEquipment(Equipment.DUMBBELL);
        exercise4 = new Exercise("Pull ups", "..", BodyPart.BACK);
        exercise4.addEquipment(Equipment.BODY_WEIGHT);
        exercise5 = new Exercise("Push ups", "..", BodyPart.CHEST);
        exercise5.addEquipment(Equipment.BODY_WEIGHT);
        filteredExerciseList.addExercise(exercise1);
        filteredExerciseList.addExercise(exercise2);
        filteredExerciseList.addExercise(exercise3);
        filteredExerciseList.addExercise(exercise4);
        filteredExerciseList.addExercise(exercise5);
    }

    private void assertFilteredExerciseListEquals(Collection<Exercise> expected){
        Collection<Exercise> actual = filteredExerciseList.getFilteredExercises();
        if(expected.containsAll(actual) && expected.size() == actual.size())
            Assertions.assertTrue(expected.containsAll(actual) && expected.size() == actual.size());
        else
            Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetFilteredExercises_noFilters(){
        assertFilteredExerciseListEquals(filteredExerciseList.getExercises());
    }

    @Test
    public void testGetFilteredExercises_withNameFilter(){
        filteredExerciseList.setNameFilter("Pu");
        Collection<Exercise> expected = Arrays.asList(exercise4, exercise5);
        assertFilteredExerciseListEquals(expected);
    }

    @Test
    public void testGetFilteredExercises_withBodyPartFilter(){
        filteredExerciseList.addBodyPartFilter(BodyPart.CHEST);
        Collection<Exercise> expected = Arrays.asList(exercise1, exercise5);
        assertFilteredExerciseListEquals(expected);
    }

    @Test
    public void testGetFilteredExercises_withEquipmentFilter(){
        filteredExerciseList.addEquipmentFilter(Equipment.BODY_WEIGHT);
        Collection<Exercise> expected = Arrays.asList(exercise4, exercise5);
        assertFilteredExerciseListEquals(expected);
    }

    @Test
    public void testGetFilteredExercises_withEquipmentAndBodyPartFilter(){
        filteredExerciseList.addEquipmentFilter(Equipment.BODY_WEIGHT);
        filteredExerciseList.addBodyPartFilter(BodyPart.CHEST);
        Collection<Exercise> expected = Arrays.asList(exercise5);
        assertFilteredExerciseListEquals(expected);
    }

    @Test
    public void testGetFilteredExercises_withNameAndBodyPartFilter(){
        filteredExerciseList.setNameFilter("Pu");
        filteredExerciseList.addBodyPartFilter(BodyPart.CHEST);
        Collection<Exercise> expected = Arrays.asList(exercise5);
        assertFilteredExerciseListEquals(expected);
    }

    @Test
    public void testGetFilteredExercises_withNameAndEquipmentFilter(){
        filteredExerciseList.addEquipmentFilter(Equipment.BARBELL);
        filteredExerciseList.setNameFilter("Bench");
        Collection<Exercise> expected = Arrays.asList(exercise1);
        assertFilteredExerciseListEquals(expected);
    }

    @Test
    public void testGetFilteredExercises_withEquipmentAndBodyPartFilterAndName(){
        filteredExerciseList.addEquipmentFilter(Equipment.BODY_WEIGHT);
        filteredExerciseList.addBodyPartFilter(BodyPart.CHEST);
        filteredExerciseList.setNameFilter("Pu");
        Collection<Exercise> expected = Arrays.asList(exercise5);
        assertFilteredExerciseListEquals(expected);
    }

    private int recivedNotification = 0;

    @Test
    public void testFireExerciseListChanged(){
        ExerciseListListener listener = exerciseList -> {
            recivedNotification++;
        };
        filteredExerciseList.addExerciseListListener(listener);
        filteredExerciseList.addEquipmentFilter(Equipment.BARBELL);
        Assertions.assertEquals(1, recivedNotification);
        filteredExerciseList.addBodyPartFilter(BodyPart.CHEST);
        Assertions.assertEquals(2, recivedNotification);
        filteredExerciseList.setNameFilter("Bench");
        Assertions.assertEquals(3, recivedNotification);
        filteredExerciseList.removeBodyPartFilter(BodyPart.CHEST);
        Assertions.assertEquals(4, recivedNotification);
        filteredExerciseList.addEquipmentFilter(Equipment.BARBELL);
        Assertions.assertEquals(4, recivedNotification);
    }
}
