package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Equipment;

import java.io.IOException;

public class EquipmentSerializer extends JsonSerializer<Equipment> {
    @Override
    public void serialize(Equipment equipment, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("name", equipment.toString());
        jsonGen.writeEndObject();
    }
}
