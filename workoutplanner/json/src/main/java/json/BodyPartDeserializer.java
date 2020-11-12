package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import core.BodyPart;

import java.io.IOException;

public class BodyPartDeserializer extends JsonDeserializer<BodyPart> {

    @Override
    public BodyPart deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode)treeNode);
    }

    BodyPart deserialize(JsonNode jsonNode) {
        if(jsonNode instanceof ObjectNode){
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode nameNode = objectNode.get("name");
            if(!(nameNode instanceof TextNode))
                return null;
            return BodyPart.valueOf(nameNode.asText());
        }
        return null;
    }
}
