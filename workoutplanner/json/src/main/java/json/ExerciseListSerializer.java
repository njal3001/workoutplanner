package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Exercise;
import core.ExerciseList;

import java.io.IOException;

public class ExerciseListSerializer extends JsonSerializer<ExerciseList> {
    @Override
    public void serialize(ExerciseList exerciseList, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeArrayFieldStart("exercises");
        for(Exercise exercise : exerciseList.getExercises())
            jsonGen.writeObject(exercise);
        jsonGen.writeEndArray();
        jsonGen.writeEndObject();
    }
}