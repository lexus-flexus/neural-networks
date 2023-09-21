package com.example.neuralnetworks.rules;

public class BipolarRuleHebb implements Rule{
    @Override
    public int calculate(int w, int x, int y) {
        return w + x * y;
    }
}
