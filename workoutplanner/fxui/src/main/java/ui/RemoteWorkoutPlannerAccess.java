package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.BodyPart;
import core.Equipment;
import core.Exercise;
import core.ExerciseList;
import json.WorkoutPlannerModule;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;

public class RemoteWorkoutPlannerAccess implements WorkoutPlannerAccess {

    private final URI endpointBaseUri;
    private final ObjectMapper objectMapper;
    private ExerciseList exerciseList;
    private Collection<Equipment> allEquipment;
    private Collection<BodyPart> allBodyParts;

    public RemoteWorkoutPlannerAccess(URI endpointBaseUri) {
        this.endpointBaseUri = endpointBaseUri;
        objectMapper = new ObjectMapper().registerModule(new WorkoutPlannerModule());
    }

    @Override
    public Collection<Exercise> getExercises() {
        if (exerciseList == null) {
            try {
                HttpRequest request = HttpRequest.newBuilder(endpointBaseUri).header("Accept", "application/json").GET()
                        .build();
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                        HttpResponse.BodyHandlers.ofString());
                final String responseString = response.body();
                exerciseList = objectMapper.readValue(responseString, ExerciseList.class);
                System.out.println("ExerciseList: " + exerciseList);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return exerciseList.getExercises();
    }

    @Override
    public void removeExercise(Exercise exercise) {
        try {
            String json = objectMapper.writeValueAsString(exercise);
            HttpRequest request = HttpRequest.newBuilder(endpointBaseUri.resolve("exerciselist/remove"))
                    .header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Boolean removed = objectMapper.readValue(responseString, Boolean.class);
            if (removed != null) {
                System.out.println("Exercise removed: " + exercise.getName());
                this.exerciseList.removeExercise(exercise);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addExercise(Exercise exercise) {
        try {
            String json = objectMapper.writeValueAsString(exercise);
            HttpRequest request = HttpRequest.newBuilder(endpointBaseUri.resolve("exerciselist/add"))
                    .header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Boolean added = objectMapper.readValue(responseString, Boolean.class);
            if (added != null) {
                System.out.println("Exercise added: " + exercise.getName());
                this.exerciseList.addExercise(exercise);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Equipment> getAllEquipment() {
        if (allEquipment == null) {
            try {
                HttpRequest request = HttpRequest.newBuilder(endpointBaseUri.resolve("equipment"))
                        .header("Accept", "application/json").GET().build();
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                        HttpResponse.BodyHandlers.ofString());
                final String responseString = response.body();
                System.out.println(Arrays.asList(mapper.readValue(responseString, Equipment[].class)));
                //this.allEquipment = objectMapper.readValue(responseString, Equipment[].class);
                System.out.println("All equipment: " + this.allEquipment);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return new ArrayList<>(allEquipment);
    }

    @Override
    public Collection<BodyPart> getAllBodyParts() {
        if (allBodyParts == null) {
            try {
                HttpRequest request = HttpRequest.newBuilder(endpointBaseUri.resolve("bodypart"))
                        .header("Accept", "application/json").GET().build();
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                        HttpResponse.BodyHandlers.ofString());
                final String responseString = response.body();
                System.out.println(Arrays.asList(mapper.readValue(responseString, BodyPart[].class)));
                //this.allBodyParts = objectMapper.readValue(responseString, BodyPart[].class);
                System.out.println("All body parts: " + this.allBodyParts);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return new ArrayList<>(allBodyParts);
    }

}
