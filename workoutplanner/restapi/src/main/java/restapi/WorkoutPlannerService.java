package restapi;

import core.BodyPart;
import core.Equipment;
import core.ExerciseList;
import core.FilteredExerciseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path(WorkoutPlannerService.WORKOUTPLANNER_SERVICE_PATH)
public class WorkoutPlannerService {

    public static final String WORKOUTPLANNER_SERVICE_PATH = "workoutplanner";

    private static final Logger LOG = LoggerFactory.getLogger(WorkoutPlannerService.class);

    @Inject
    private ExerciseList exerciseList;


    @Path("/exerciseList")
    public ExerciseListResource getExerciseList(){
        LOG.debug("Sub - resource for ExerciseList: " + exerciseList);
        return new ExerciseListResource(exerciseList);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/equipment")
    public Collection<Equipment> getAllEquipment(){
        LOG.debug("getAllEquipment called");
        return Equipment.getAllEquipment();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/bodypart")
    public Collection<BodyPart> getAllBodyParts(){
        LOG.debug("getAllBodyParts called");
        return BodyPart.getAllBodyParts();
    }
}
