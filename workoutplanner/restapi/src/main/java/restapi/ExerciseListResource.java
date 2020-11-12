package restapi;

import core.Exercise;
import core.ExerciseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class ExerciseListResource {

    private final Logger LOG = LoggerFactory.getLogger(ExerciseListResource.class);

    private final ExerciseList exerciseList;

    public ExerciseListResource(ExerciseList exerciseList){
        this.exerciseList = exerciseList;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ExerciseList getExerciseList(){
        LOG.debug("getExerciseList called");
        return exerciseList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public void addExercise(Exercise exercise){
        LOG.debug("Exercise added: " + exercise);
        this.exerciseList.addExercise(exercise);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/remove")
    public void removeExercise(Exercise exercise){
        LOG.debug("Exercise removed: " + exercise);
        this.exerciseList.removeExercise(exercise);
    }
}
