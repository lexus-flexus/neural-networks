package com.example.neuralnetworks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class Controller {

//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//      }
    @FXML
    private ChoiceBox<Integer> choiceBox;
    @FXML
    private GridPane grid;
    @FXML
    private TextField textField;
    @FXML
    private Button myButton;
    private int[][] gridData;


    @FXML
    public void initialize() {
        ObservableList<Integer> availableChoices = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        choiceBox.setItems(availableChoices);
        choiceBox.setValue(5);  // Default grid size
        resize();  // Create the initial default grid

        // Setup button event handler
        myButton.setOnAction(e -> {
            StringBuilder builder = new StringBuilder();
            for (int[] row : gridData)
                builder.append(Arrays.toString(row)).append('\n');
            textField.setText(builder.toString());
        });
    }

    public void resize() {
        grid.getChildren().clear();  // Clear the grid first
        int gridSize = choiceBox.getValue();
        gridData = new int[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Rectangle rect = new Rectangle(30, 30, Color.WHITE);
//                rect.setStroke(Color.BLACK);

                // Cache in the lambda expression i-th and j-th cell indexes.
                final int fi = i;
                final int fj = j;

                rect.setOnMouseClicked(e -> {
                    if (rect.getFill().equals(Color.WHITE)) {
                        rect.setFill(Color.BLACK);
                        gridData[fi][fj] = 1;
                    } else {
                        rect.setFill(Color.WHITE);
                        gridData[fi][fj] = 0;
                    }
                });

                grid.add(rect, j, i);
            }
        }
    }
}