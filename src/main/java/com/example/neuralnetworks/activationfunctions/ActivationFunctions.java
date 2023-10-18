package com.example.neuralnetworks.activationfunctions;

import java.util.List;

public enum ActivationFunctions {

    BINARY("Бинарная функция активации", new BinaryFunctionActivation()),
    BIPOLAR("Биполярная функция активации", new BipolarActivationFunction());

    private String name;
    private ActivationFunction function;

    ActivationFunctions(String name, ActivationFunction function) {
        this.name = name;
        this.function = function;
    }

    public static List<String> getAllEnumStringValues() {
        return List.of(BINARY.name, BIPOLAR.name);
    }

}
