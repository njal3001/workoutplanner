package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.BodyPart;
import json.WorkoutPlannerModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BodyPartPersistenceTest {

    private static final BodyPart bodyPart = BodyPart.BACK;

    @Test
    public void persistenceTest(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new WorkoutPlannerModule());
        String result = null;

        try{
            result = objectMapper.writeValueAsString(bodyPart);
        } catch(JsonProcessingException e){
            fail(e);
        }

        try{
            BodyPart newBodyPart = objectMapper.readValue(result, BodyPart.class);
            assertEquals(bodyPart, newBodyPart);
        } catch(JsonProcessingException e){
            fail(e);
        }
    }
}
