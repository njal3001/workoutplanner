package ui;

import core.BodyPart;
import core.Equipment;
import core.Exercise;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.Collection;


public class NewExerciseController implements SceneController{

    @FXML
    TextField exerciseNameField;
    @FXML
    TextArea exerciseDescriptionArea;
    @FXML
    FlowPane bodyPartPane;
    @FXML
    FlowPane equipmentPane;

    private ScenesController scenesController;

    public void setScenesController(ScenesController scenesController) {
        this.scenesController = scenesController;
    }


    private Collection<CheckBox> checkBoxes = new ArrayList<>();

    private Collection<Equipment> selectedEquipment = new ArrayList<>();

    private void createChoiceBoxes_Equipment() {
        for (Equipment equipment : scenesController.getAccess().getAllEquipment()) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(equipment.toString());
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if(checkBox.isSelected())
                    selectedEquipment.add(equipment);
                else
                    selectedEquipment.remove(equipment);
            });
            checkBoxes.add(checkBox);
            equipmentPane.getChildren().add(checkBox);
        }
    }

    private BodyPart selectedBodyPart;
    
    private void createRadioButtons_BodyParts(){
        ToggleGroup toggleGroup = new ToggleGroup();
        for(BodyPart bodyPart : scenesController.getAccess().getAllBodyParts()){
            RadioButton radioButton = new RadioButton();
            radioButton.setText(bodyPart.toString());
            radioButton.setToggleGroup(toggleGroup);
            radioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                selectedBodyPart = bodyPart;
            });
            bodyPartPane.getChildren().add(radioButton);
        }
    }


    private boolean buttonsCreated = false;

    @Override
    public void onSceneDisplayed() {
        if(!buttonsCreated){
            createChoiceBoxes_Equipment();
            createRadioButtons_BodyParts();
            buttonsCreated = true;
        }
        exerciseNameField.setText("");
        exerciseDescriptionArea.setText("");
        ((RadioButton)bodyPartPane.getChildren().get(0)).selectedProperty().setValue(true);
        checkBoxes.stream().forEach(c -> c.setSelected(false));
    }

    public void handleAddExercise() {
        String name = exerciseNameField.getText();
        String description = exerciseDescriptionArea.getText();
        Exercise exercise = new Exercise(name, description, selectedBodyPart);
        exercise.addEquipment(selectedEquipment);
        scenesController.getAccess().addExercise(exercise);
        scenesController.setScene(App.EXERCISES_ID);
    }

    public void handleCancel(){
        scenesController.setScene(App.EXERCISES_ID);
    }
}
