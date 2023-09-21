module com.example.neuralnetworks {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.neuralnetworks to javafx.fxml;
    exports com.example.neuralnetworks;
    exports com.example.neuralnetworks.rules;
    opens com.example.neuralnetworks.rules to javafx.fxml;
}