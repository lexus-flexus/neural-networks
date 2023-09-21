package com.example.neuralnetworks.rules;

public class BinaryRuleHebb implements Rule {
    @Override
    public int calculate(int w, int x, int y) {
        int deltaW;
        if (x == 1 && y == 1) {
            deltaW = 1;
        } else if (x == 0) {
            deltaW = 0;
        } else {
            deltaW = -1;
        }
        return w + deltaW;
    }
}
