package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import core.Exercise;
import core.ExerciseList;

import java.io.IOException;

public class ExerciseListDeserializer extends JsonDeserializer<ExerciseList> {

    private ExerciseDeserializer exerciseDeserializer = new ExerciseDeserializer();

    @Override
    public ExerciseList deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    ExerciseList deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            ExerciseList exerciseList = new ExerciseList();
            JsonNode exercisesNode = objectNode.get("exercises");
            if(exercisesNode instanceof ArrayNode){
                for(JsonNode elementNode : (ArrayNode) exercisesNode){
                    Exercise exercise = exerciseDeserializer.deserialize(elementNode);
                    if(exercise != null)
                        exerciseList.addExercise(exercise);
                }
            }
            return exerciseList;
        }
        return null;
    }
}
