package com.example.neuralnetworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class NeuralNetworkTest {

    @Test
    void fit() {

        int x[][] = new int[][]{
                {1, 1, -1, 1, 1, 1, 1, -1, -1, 1},
                {1, 1, 1, 1, 1, -1, 1, 1, -1, 1}
        };
        int y[] = new int[]{1, -1};
        NeuralNetwork neuralNetwork = new NeuralNetwork(1, x[0].length, SignalRepresentation.BIPOLAR);
        neuralNetwork.fit(x, y);
        List<Neuron> neurons = neuralNetwork.getNeuronList();
        int[] actual = neurons.get(0).getWeight();
        int[] expected = new int[]{0, 0, -2, 0, 0, 2, 0, -2, 0, 0};
        Assertions.assertArrayEquals(expected, actual);
    }
}