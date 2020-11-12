package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.ExerciseList;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class WorkoutPlannerPersistence {

    private ObjectMapper objectMapper;

    public WorkoutPlannerPersistence(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new WorkoutPlannerModule());
    }

    public ExerciseList readExerciseList(Reader reader) throws IOException{
        return objectMapper.readValue(reader, ExerciseList.class);
    }

    public void writeExerciseList(ExerciseList exerciseList, Writer writer) throws IOException{
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, exerciseList);
    }

}
