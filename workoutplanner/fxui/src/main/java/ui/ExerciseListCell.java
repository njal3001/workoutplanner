package ui;

import core.Exercise;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ExerciseListCell extends ListCell<Exercise> {


    private TextFlow textFlow = new TextFlow();
    private Text descriptionText = new Text();
    private Text nameText = new Text();

    public ExerciseListCell(){
        nameText.setStyle("-fx-font-weight: bold");
        textFlow.getChildren().add(nameText);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            private boolean isExpanded = false;

            @Override
            public void handle(MouseEvent event) {
                if(getItem() == null)
                    return;

                if (!isExpanded)
                    textFlow.getChildren().add(descriptionText);
                else
                    textFlow.getChildren().remove(descriptionText);
                isExpanded = !isExpanded;
                setGraphic(textFlow);
            }
        });
    }

    @Override
    public void updateItem(Exercise exercise, boolean empty) {
        super.updateItem(exercise, empty);
        setText(null);
        if (empty || exercise == null) {
            setGraphic(null);
        } else {
            nameText.setText(exercise.getName());
            setGraphic(textFlow);
            descriptionText.setText("\nDescription:" + "\n" + exercise.getDescription());
        }
    }
}
