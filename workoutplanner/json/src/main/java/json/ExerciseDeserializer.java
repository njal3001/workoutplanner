package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import core.BodyPart;
import core.Equipment;
import core.Exercise;

import java.io.IOException;

public class ExerciseDeserializer extends JsonDeserializer<Exercise> {

    private BodyPartDeserializer bodyPartDeserializer = new BodyPartDeserializer();
    private EquipmentDeserializer equipmentDeserializer = new EquipmentDeserializer();


    @Override
    public Exercise deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    Exercise deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode nameNode = objectNode.get("name");
            if (!(nameNode instanceof TextNode))
                return null;
            JsonNode descriptionNode = objectNode.get("description");
            if(!(descriptionNode instanceof TextNode))
                return null;
            JsonNode bodyPartNode = objectNode.get("bodypart");
            BodyPart bodyPart = bodyPartDeserializer.deserialize(bodyPartNode);
            if(bodyPart == null)
                return null;
            Exercise exercise = new Exercise(nameNode.asText(), descriptionNode.asText(), bodyPart);
            JsonNode equipmentNode = objectNode.get("equipment");
            if(equipmentNode instanceof ArrayNode){
                for(JsonNode elementNode : (ArrayNode) equipmentNode){
                    Equipment equipment = equipmentDeserializer.deserialize(elementNode);
                    if(equipment != null)
                        exercise.addEquipment(equipment);
                }
            }
            return exercise;
        }
        return null;
    }
}
