package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.BodyPart;

import java.io.IOException;

public class BodyPartSerializer extends JsonSerializer<BodyPart> {
    @Override
    public void serialize(BodyPart bodyPart, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("name", bodyPart.toString());
        jsonGen.writeEndObject();
    }
}
