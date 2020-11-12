package json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import core.BodyPart;
import core.Equipment;
import core.Exercise;
import core.ExerciseList;

public class WorkoutPlannerModule extends SimpleModule {

    public WorkoutPlannerModule(){
        addSerializer(BodyPart.class, new BodyPartSerializer());
        addSerializer(Equipment.class, new EquipmentSerializer());
        addSerializer(Exercise.class, new ExerciseSerializer());
        addSerializer(ExerciseList.class, new ExerciseListSerializer());

        addDeserializer(BodyPart.class, new BodyPartDeserializer());
        addDeserializer(Equipment.class, new EquipmentDeserializer());
        addDeserializer(Exercise.class, new ExerciseDeserializer());
        addDeserializer(ExerciseList.class, new ExerciseListDeserializer());
    }
}
