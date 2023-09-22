package com.example.neuralnetworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeuronTest {

    int length;
    public Neuron neuron;

    @BeforeEach
   public void setUp(){

        length = 5;
        neuron = new Neuron(length);

    }

    @Test
    void setWeightToZero() {

        neuron.setWeightToZero();

        int[] expected = new int[length];
        for(int i = 0; i < expected.length; i++){
            expected[i] = 0;
        }

        Assertions.assertArrayEquals(expected, neuron.getWeight());

    }

}