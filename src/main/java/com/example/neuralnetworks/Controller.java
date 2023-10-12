package com.example.neuralnetworks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private int[][][] vectorX;
    private final int countImages = 2;
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
    @FXML
    public void resize() {
        grid.getChildren().clear();

        vectorX = new int[countImages][][];
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
    @FXML
    public void saveX1(){

        vectorX[0] = copyArray(gridData);

    }
    @FXML
    public void saveX2(){

        vectorX[1] = copyArray(gridData);

    }
    @FXML
    public void loadX1(){
        if(vectorX[0] != null){

            gridData = copyArray(vectorX[0]);
            updateGrid(gridData);
        }
    }
    @FXML
    public void loadX2(){
        if(vectorX[1] != null){

            gridData = copyArray(vectorX[1]);
            updateGrid(gridData);
        }
    }

    private void updateGrid(int[][] gridData){
        for(int i = 0; i < gridData.length; i++){
            for(int j = 0; j < gridData[i].length; j++){

                Color color = (gridData[i][j] == 1) ? Color.BLACK : Color.WHITE;
                for (Node node : grid.getChildren()) {
                    if (GridPane.getColumnIndex(node) == j && GridPane.getRowIndex(node) == i) {
                        Rectangle rect = (Rectangle) node;
                        rect.setFill(color);
                    }
                }
            }
        }
    }

    private int[][] copyArray(int[][] array){

        int[][] copyArray = new int[array.length][array[0].length];
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                copyArray[i][j] = array[i][j];
            }
        }
        return copyArray;
    }

}