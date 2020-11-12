package ui;

import core.BodyPart;
import core.Equipment;
import core.Exercise;
import core.FilteredExerciseList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.Collection;


public class ExercisesController implements SceneController{

    @FXML
    TextField searchBar;
    @FXML
    FlowPane bodyPartPane;
    @FXML
    FlowPane equipmentPane;
    @FXML
    ListView<Exercise> exercisesView;
    @FXML
    Button deleteExerciseButton;


    private FilteredExerciseList filteredExerciseList;
    private ScenesController scenesController;

    public ExercisesController(){
        filteredExerciseList = new FilteredExerciseList();
    }

    @Override
    public void setScenesController(ScenesController scenesController) {
        this.scenesController = scenesController;
    }

    @FXML
    public void initialize() {
        filteredExerciseList.addExerciseListListener(list -> {updateView();});
        createChoiceBoxes_Equipment();
        createChoiceBoxes_BodyPart();
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 0)
                filteredExerciseList.setNameFilter(null);
            else
                filteredExerciseList.setNameFilter(newValue);
        });
        exercisesView.setCellFactory(exercisesView -> {
            ExerciseListCell listCell = new ExerciseListCell();
            return listCell;
        });
        exercisesView.getSelectionModel().selectedItemProperty().
                addListener(listener -> updateDeleteButton());
        updateDeleteButton();
    }

    private Collection<CheckBox> checkboxes = new ArrayList<CheckBox>();

    private void createChoiceBoxes_Equipment() {
        for (Equipment equipment : Equipment.getAllEquipment()) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(equipment.toString());
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if(checkBox.isSelected())
                    filteredExerciseList.addEquipmentFilter(equipment);
                else
                    filteredExerciseList.removeEquipmentFilter(equipment);
                updateView();
            });
            checkboxes.add(checkBox);
            equipmentPane.getChildren().add(checkBox);
        }
    }

    private void createChoiceBoxes_BodyPart() {
        for (BodyPart bodyPart : BodyPart.getAllBodyParts()) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(bodyPart.toString());
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if(checkBox.isSelected())
                    filteredExerciseList.addBodyPartFilter(bodyPart);
                else
                    filteredExerciseList.removeBodyPartFilter(bodyPart);
            });
            checkboxes.add(checkBox);
            bodyPartPane.getChildren().add(checkBox);
        }
    }

    @Override
    public void onSceneDisplayed() {
        filteredExerciseList.clear();
        //System.out.println(scenesController.getAccess());
        filteredExerciseList.addExercise(scenesController.getAccess().getExercises());
        searchBar.setText("");
        checkboxes.stream().forEach(c -> c.setSelected(false));
        exercisesView.getSelectionModel().select(-1);
    }

    private void updateView() {
        exercisesView.getItems().setAll(filteredExerciseList.getFilteredExercises());
    }

    private void updateDeleteButton(){
        deleteExerciseButton.setDisable(exercisesView.getSelectionModel().getSelectedIndex() == -1);
    }

    @FXML
    public void handleAddNewExerciseAction(){
        scenesController.setScene(App.NEW_EXERCISE_ID);
    }

    @FXML
    public void handleDeleteExerciseAction(){
        int index = exercisesView.getSelectionModel().getSelectedIndex();
        Exercise exercise = exercisesView.getItems().get(index);
        if(exercise != null) {
            filteredExerciseList.removeExercise(exercise);
            scenesController.getAccess().removeExercise(exercise);
        }
    }
}
