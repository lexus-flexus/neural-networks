package com.example.neuralnetworks;

import com.example.neuralnetworks.activationfunctions.ActivationFunction;
import com.example.neuralnetworks.activationfunctions.BinaryFunctionActivation;
import com.example.neuralnetworks.activationfunctions.BipolarActivationFunction;
import com.example.neuralnetworks.rules.BinaryRuleHebb;
import com.example.neuralnetworks.rules.BipolarRuleHebb;
import com.example.neuralnetworks.rules.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeuralNetwork {

    private final List<Neuron> neuronList = new ArrayList<>();
    private final SignalRepresentation signalRepresentation;
    private final Rule rule;

    private final ActivationFunction activationFunction;

/**
 *     int k - count neurons
 *     int countW - count weight in neuron
 */
    public NeuralNetwork(int k, int countW, SignalRepresentation signalRepresentation) {

        for (int i = 0; i < k; i++) {
            this.neuronList.add(new Neuron(countW));
        }

        this.signalRepresentation = signalRepresentation;
        switch (signalRepresentation) {
            case BIPOLAR -> {rule = new BipolarRuleHebb();
                            activationFunction = new BipolarActivationFunction();}
            case BINARY -> {rule = new BinaryRuleHebb();
                            activationFunction = new BinaryFunctionActivation();}
            default -> throw new IllegalStateException("Unexpected value: " + signalRepresentation);
        }

    }

    public void fit(int[][] x, int[] y) {
        for (Neuron neuron : this.neuronList) {
            neuron.setWeightToZero();
        }

        do {
            for (Neuron neuron : this.neuronList) {
                correctionWeight(neuron, x, y);
            }
        }while (!this.isTrained(x, y));

    }

    public List<Neuron> getNeuronList() {
        return neuronList;
    }

    private void correctionWeight(Neuron neuron, int[][] x, int[] y) {

        int[] w = neuron.getWeight();
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length && j < w.length; j++) {

                w[j] = this.rule.calculate(w[j], x[i][j], y[i]);

            }
        }
        neuron.setWeight(w);
    }

    private int[] summer(int[][] x) {

        int[] sum = new int[x.length];
        for (Neuron neuron : this.neuronList) {

            int[] w = neuron.getWeight();
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x[i].length; j++) {

                    sum[i] += x[i][j] * w[j];

                }
            }
        }
        return sum;
    }

    private boolean isTrained(int[][] x, int[] y){

        int[] sum = this.summer(x);
        int[] actualY = new int[y.length];
        for(int i = 0; i < y.length; i++){
            actualY[i] = activationFunction.execute(sum[i]);
        }
        return Arrays.equals(y, actualY);
    }

}

