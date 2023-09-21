package com.example.neuralnetworks;

import java.util.Arrays;

public class Neuron {

    private int[] weight;

    public Neuron(int countW) {
        this.weight = new int[countW];
    }

    public void setWeightToZero(){
        Arrays.fill(weight, 0);
    }

    public int[] getWeight() {
        return weight;
    }

    public void setWeight(int[] weight) {
        this.weight = weight;
    }
}
