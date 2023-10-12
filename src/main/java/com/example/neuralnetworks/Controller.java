package com.example.neuralnetworks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class Controller {

    @FXML
    private ChoiceBox<Integer> choiceBox;
    @FXML
    private GridPane grid;
    @FXML
    private TextField textField;
    @FXML
    private Button myButton;
    private int[][] gridData;
    private final double gridWidth = 300.0;
    private final double gridHeight = 300.0;

    @FXML
    public void initialize() {
        ObservableList<Integer> availableChoices = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        choiceBox.setItems(availableChoices);
        choiceBox.setValue(5);
        grid.setPrefHeight(gridHeight);
        grid.setPrefWidth(gridWidth);
        resize();

        myButton.setOnAction(e -> {
            StringBuilder builder = new StringBuilder();
            for (int[] row : gridData)
                builder.append(Arrays.toString(row)).append('\n');
            textField.setText(builder.toString());
        });
    }

    public void resize() {
        grid.getChildren().clear();
        int gridSizeHeight = choiceBox.getValue();
        int gridSizeWidth = choiceBox.getValue();
        gridData = new int[gridSizeHeight][gridSizeWidth];
       double rectangleWidth = gridWidth / gridSizeWidth;
       double rectangleHeight = gridHeight / gridSizeHeight;

        for (int i = 0; i < gridSizeHeight; i++) {
            for (int j = 0; j < gridSizeWidth; j++) {
                Rectangle rect = getRectangle(i, j, rectangleWidth, rectangleHeight);

                grid.add(rect, j, i);
            }
        }
    }

    private Rectangle getRectangle(int i, int j, double rectangleWidth, double rectangleHeight) {
        Rectangle rect = new Rectangle(rectangleWidth, rectangleHeight, Color.WHITE);

        rect.setOnMouseClicked(e -> {
            if (rect.getFill().equals(Color.WHITE)) {
                rect.setFill(Color.BLACK);
                gridData[i][j] = 1;
            } else {
                rect.setFill(Color.WHITE);
                gridData[i][j] = 0;
            }
        });
        return rect;
    }


}