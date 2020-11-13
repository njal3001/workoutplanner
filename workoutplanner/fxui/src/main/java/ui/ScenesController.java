package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ScenesController {

    private Stage stage;

    private Map<String, Scene> scenes = new HashMap<String, Scene>();
    private Map<String, SceneController> sceneControllers = new HashMap<String, SceneController>();

    private WorkoutPlannerAccess access;

    public ScenesController(Stage stage, WorkoutPlannerAccess access){
        this.stage = stage;
        this.access = access;
    }

    public WorkoutPlannerAccess getAccess(){
        return access;
    }

    public void loadScene(String name, String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(resource));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        SceneController sceneController = (SceneController) loader.getController();
        sceneController.setScenesController(this);
        scenes.put(name, scene);
        sceneControllers.put(name, sceneController);
    }

    public void unloadScene(String name){
        scenes.remove(name);
        sceneControllers.remove(name);
    }

    public Scene getScene(String name){
        return scenes.get(name);
    }

    public void setScene(String name){
        getSceneController(name).onSceneDisplayed();
        stage.setScene(getScene(name));
    }

    public SceneController getSceneController(String name){
        return sceneControllers.get(name);
    }
}
