package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.BodyPart;
import core.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EquipmentPersistenceTest {

    private static final Equipment equipment = Equipment.BARBELL;

    @Test
    public void persistenceTest(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new WorkoutPlannerModule());
        String result = null;

        try{
            result = objectMapper.writeValueAsString(equipment);
        } catch(JsonProcessingException e){
            fail(e);
        }

        try{
            Equipment newEquipment = objectMapper.readValue(result, Equipment.class);
            assertEquals(equipment, newEquipment);
        } catch(JsonProcessingException e){
            fail(e);
        }
    }
}
