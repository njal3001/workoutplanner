package ui;

import core.ExerciseList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import json.WorkoutPlannerPersistence;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class AppInitializer {

    private WorkoutPlannerAccess access;
    private ExerciseList exerciseList;

    public AppInitializer(Stage stage, String userExerciseListPath, String endpointUri){
        if(endpointUri != null){
            RemoteWorkoutPlannerAccess remoteAccess;
            try{
                System.out.println("Using remote endpoint @ " + endpointUri);
                remoteAccess = new RemoteWorkoutPlannerAccess(new URI(endpointUri));
                access = remoteAccess;
            } catch(URISyntaxException e){
                System.err.println(e);
            }
        }
        if(userExerciseListPath != null){
            DirectWorkoutPlannerAccess directAccess = new
                    DirectWorkoutPlannerAccess(getInitalExerciseList(stage, userExerciseListPath));
            access = directAccess;
        }
    }

    public WorkoutPlannerAccess getAccess(){
        return this.access;
    }

    private ExerciseList getInitalExerciseList(Stage stage, String userExerciseListPath){
        exerciseList = new ExerciseList();
        if(userExerciseListPath != null) {
            WorkoutPlannerPersistence workoutPlannerPersistence = new WorkoutPlannerPersistence();
            try {
                Reader reader = new FileReader(Paths.get(System.getProperty("user.home"), userExerciseListPath).toFile(),
                        StandardCharsets.UTF_8);
                exerciseList = workoutPlannerPersistence.readExerciseList(reader);
                reader.close();
            } catch (IOException e) {
                System.err.println("Could not find " + userExerciseListPath + " on home directory, creating new exercise list instead");
                e.printStackTrace();
            }
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    WorkoutPlannerPersistence workoutPlannerPersistence = new WorkoutPlannerPersistence();
                    try{
                        Writer writer =
                                new FileWriter(Paths.get(System.getProperty("user.home"), userExerciseListPath).toFile(),
                                        StandardCharsets.UTF_8);
                        workoutPlannerPersistence.writeExerciseList(exerciseList, writer);
                    } catch (IOException e){
                        System.err.println("Could not write to " + userExerciseListPath + " on home directory");
                        e.printStackTrace();
                    }
                }
            });
        }
        return exerciseList;
    }
}
