package com.example.neuralnetworks.activationfunctions;

public class BinaryFunctionActivation implements ActivationFunction{
    @Override
    public int execute(int sum) {
        return sum >= 0 ? 1 : -1;
    }
}
