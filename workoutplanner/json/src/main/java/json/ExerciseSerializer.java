package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Equipment;
import core.Exercise;

import java.io.IOException;

public class ExerciseSerializer extends JsonSerializer<Exercise> {
    @Override
    public void serialize(Exercise exercise, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        if(exercise.getName() != null)
            jsonGen.writeStringField("name", exercise.getName());
        if(exercise.getDescription() != null)
            jsonGen.writeStringField("description", exercise.getDescription());
        jsonGen.writeObjectField("bodypart", exercise.getBodyPart());
        jsonGen.writeArrayFieldStart("equipment");
        for (Equipment equipment : exercise.getEquipment())
            jsonGen.writeObject(equipment);
        jsonGen.writeEndArray();
        jsonGen.writeEndObject();
    }
}
