package restserver;


import com.fasterxml.jackson.databind.ObjectMapper;
import json.WorkoutPlannerModule;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WorkoutPlannerModuleObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new WorkoutPlannerModule());

    public ObjectMapper getContext(final Class<?> type) {
        return objectMapper;
    }
}
