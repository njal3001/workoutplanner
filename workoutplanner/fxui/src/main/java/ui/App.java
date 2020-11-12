package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class App extends Application {

    public static final String EXERCISES_ID = "Exercises";
    private static final String EXERCISES_FILE = "Exercises.fxml";

    public static final String NEW_EXERCISE_ID = "NewExercise";
    private static final String NEW_EXERCISE_FILE = "NewExercise.fxml";

    @Override
    public void start(Stage stage) throws Exception {

        AppInitializer initializer = new AppInitializer(stage, "exerciselist.json", null);

        System.out.println(initializer.getAccess());
        ScenesController scenesController = new ScenesController(stage, initializer.getAccess());

        scenesController.loadScene(EXERCISES_ID, EXERCISES_FILE);
        scenesController.loadScene(NEW_EXERCISE_ID, NEW_EXERCISE_FILE);

        scenesController.setScene(EXERCISES_ID);
        stage.setTitle("Workout Planner");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
