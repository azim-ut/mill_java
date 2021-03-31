package com.example.mill.bean;

import java.util.Queue;

import com.example.mill.Mill;

public class MillState {
    private final int water;
    private final int millet;
    private final int flour;
    private final int power;

    private final boolean engine;
    private final boolean machine;

    public MillState(Mill mill, Queue<Water> water, Queue<Millet> mallets, Queue<Flour> flours) {
        this.water = water.size();
        this.millet = mallets.size();
        this.flour = flours.size();

        power = mill.getPower();
        machine = mill.isMachineOn();
        engine = mill.isEngineOn();
    }

    public int getWater() {
        return water;
    }

    public int getMillet() {
        return millet;
    }

    public int getFlour() {
        return flour;
    }

    public int getPower() {
        return power;
    }

    public boolean isEngine() {
        return engine;
    }

    public boolean isMachine() {
        return machine;
    }
}
