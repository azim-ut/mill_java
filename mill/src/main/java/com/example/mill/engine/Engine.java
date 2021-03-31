package com.example.mill.engine;

public class Engine extends Thread {
    protected int power = 0;

    public int getPower() {
        return power;
    }

    public void incPower(int val) {
        power += val;
    }

    public void decPower(int val) {
        power -= val;
        if (power < 0) {
            power = 0;
        }
    }
}
