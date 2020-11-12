package restserver;

import core.ExerciseList;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import restapi.WorkoutPlannerService;

public class WorkoutPlannerConfig extends ResourceConfig {

    private ExerciseList exerciseList;

    public WorkoutPlannerConfig(ExerciseList exerciseList){
        setExerciseList(exerciseList);
        register(WorkoutPlannerService.class);
        register(WorkoutPlannerModuleObjectMapperProvider.class);
        register(JacksonFeature.class);
        register(new AbstractBinder() {
            @Override
            protected void configure(){
                bind(WorkoutPlannerConfig.this.exerciseList);
            }
        });
    }

    public WorkoutPlannerConfig(){
        this(new ExerciseList());
    }

    public ExerciseList getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(ExerciseList exerciseList){
        this.exerciseList = exerciseList;
    }
}
