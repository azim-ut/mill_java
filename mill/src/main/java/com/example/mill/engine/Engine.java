package com.example.mill.engine;

public abstract class Engine extends Thread {
    protected int power = 0;

    public int getPower() {
        return power;
    }

    public void incPower(int value) {
        power += value;
    }

    public void decPower(int value) {
        power -= value;
        if (power < 0) {
            power = 0;
        }
    }
}
