package com.example.neuralnetworks;

import com.example.neuralnetworks.rules.BinaryRuleHebb;
import com.example.neuralnetworks.rules.BipolarRuleHebb;
import com.example.neuralnetworks.rules.Rule;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private final List<Neuron> neuronList = new ArrayList<>();
    private final SignalRepresentation signalRepresentation;
    private final Rule rule;

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
            case BIPOLAR -> rule = new BipolarRuleHebb();
            case BINARY -> rule = new BinaryRuleHebb();
            default -> throw new IllegalStateException("Unexpected value: " + signalRepresentation);
        }

    }

    public void fit(int[][] x, int[] y) {
        for (Neuron neuron : this.neuronList) {
            neuron.setWeightToZero();
        }

        for (Neuron neuron : this.neuronList) {
            correctionWeight(neuron, x, y);
        }

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

    private int summer(int[][] x) {

        int sum = 0;
        for (Neuron neuron : this.neuronList) {

            int[] w = neuron.getWeight();
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x[i].length; j++) {

                    sum += x[i][j] * w[j];

                }
            }
        }
        return sum;
    }

}

