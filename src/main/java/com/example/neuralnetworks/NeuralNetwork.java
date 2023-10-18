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
    private final int MAX_ITERATIONS = 10000;

    /**
     * int k - count neurons
     * int countW - count weight in neuron
     */
    public NeuralNetwork(int countNeurons, int lengthWeight, SignalRepresentation signalRepresentation) {

        for (int i = 0; i < countNeurons; i++) {
            this.neuronList.add(new Neuron(lengthWeight));
        }

        this.signalRepresentation = signalRepresentation;
        switch (signalRepresentation) {
            case BIPOLAR -> {
                rule = new BipolarRuleHebb();
                activationFunction = new BipolarActivationFunction();
            }
            case BINARY -> {
                rule = new BinaryRuleHebb();
                activationFunction = new BinaryFunctionActivation();
            }
            default -> throw new IllegalStateException("Unexpected value: " + signalRepresentation);
        }
    }


    public void fit(int[][] x, int[] y, int maxIterations) {

        for (Neuron neuron : this.neuronList) {
            neuron.setWeightToZero();
        }
        int iteration = 0;
        do {
            for (Neuron neuron : this.neuronList) {
                correctionWeight(neuron, x, y);
            }
            iteration++;
        } while (!this.isTrained(x, y) && iteration < maxIterations);
    }

    public void fit(int[][] x, int[] y) {

        this.fit(x, y, this.MAX_ITERATIONS);
    }

    public int predict(int[] x) {
        return this.activationFunction.execute(this.summer(x));
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

    private int summer(int[] x) {

        int sum = 0;
        for (Neuron neuron : this.neuronList) {

            int[] w = neuron.getWeight();
            for (int i = 0; i < x.length; i++) {

                sum += x[i] * w[i];
            }
        }
        return sum;
    }

    private boolean isTrained(int[][] x, int[] y) {

        int[] sum = new int[x.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = this.summer(x[i]);
        }

        int[] actualY = new int[y.length];
        for (int i = 0; i < y.length; i++) {
            actualY[i] = this.activationFunction.execute(sum[i]);
        }
        return Arrays.equals(y, actualY);
    }
}

