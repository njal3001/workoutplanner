package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.BodyPart;
import core.Equipment;
import core.Exercise;
import core.ExerciseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseListPersistenceTest {

    private ExerciseList exerciseList;

    @BeforeEach
    public void setUp(){
        exerciseList = new ExerciseList();

        Exercise exercise1 = new Exercise("Bench press", "Description", BodyPart.CHEST);
        exercise1.addEquipment(Equipment.BARBELL);
        exercise1.addEquipment(Equipment.BENCH);
        Exercise exercise2 = new Exercise("Squat", "Description", BodyPart.UPPER_LEG);
        exercise2.addEquipment(Equipment.BARBELL);
        Exercise exercise3 = new Exercise("Shoulder press", "Description", BodyPart.SHOULDERS);
        exercise3.addEquipment(Equipment.DUMBBELL);

        exerciseList.addExercise(List.of(exercise1, exercise2, exercise3));
    }

    @Test
    public void persistenceTest(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new WorkoutPlannerModule());
        String result = null;

        try{
            result = objectMapper.writeValueAsString(exerciseList);
        } catch(JsonProcessingException e){
            fail(e);
        }

        try{
            ExerciseList newExerciseList = objectMapper.readValue(result, ExerciseList.class);
            Iterator<Exercise> iter1 = exerciseList.iterator();
            Iterator<Exercise> iter2 = exerciseList.iterator();
            while(iter1.hasNext() && iter2.hasNext())
                 ExercisePersistenceTest.checkExercise(iter1.next(), iter2.next());
            assertFalse(iter1.hasNext());
            assertFalse(iter2.hasNext());

        } catch(JsonProcessingException e){
            fail(e);
        }
    }
}
