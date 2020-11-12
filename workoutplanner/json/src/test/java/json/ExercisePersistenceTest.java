package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.BodyPart;
import core.Equipment;
import core.Exercise;
import core.ExerciseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExercisePersistenceTest {

    private Exercise exercise;

    @BeforeEach
    public void setUp(){
        exercise = new Exercise("Bench press", "Description", BodyPart.CHEST);
        exercise.addEquipment(Equipment.BARBELL);
        exercise.addEquipment(Equipment.BENCH);
    }

    @Test
    public void persistenceTest(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new WorkoutPlannerModule());
        String result = null;

        try{
            result = objectMapper.writeValueAsString(exercise);
        } catch(JsonProcessingException e){
            e.printStackTrace();
            fail(e);
        }

        try{
            Exercise newExercise = objectMapper.readValue(result, Exercise.class);
            checkExercise(exercise, newExercise);
        } catch(JsonProcessingException e){
            fail(e);
        }
    }

    protected static void checkExercise(Exercise exercise1, Exercise exercise2){
        assertEquals(exercise1.getName(), exercise2.getName());
        assertEquals(exercise1.getDescription(), exercise2.getDescription());
        assertEquals(exercise1.getBodyPart(), exercise2.getBodyPart());
        assertTrue(exercise1.getEquipment().containsAll(exercise2.getEquipment()) &&
                exercise1.getEquipment().size() == exercise2.getEquipment().size());
    }
}
