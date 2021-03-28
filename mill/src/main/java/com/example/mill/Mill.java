package com.example.mill;

import java.util.Queue;

import com.example.mill.bean.Flour;
import com.example.mill.bean.Millet;
import com.example.mill.bean.Water;
import com.example.mill.engine.Engine;
import com.example.mill.engine.WaterWheel;
import com.example.mill.millstone.Machine;
import com.example.mill.millstone.MillStone;


/**
 * Hello world!
 */
public class Mill extends Thread {

    private final Engine engine;
    private final Machine machine;

    public Mill(Queue<Water> waterFlow, Queue<Millet> milletFlow, Queue<Flour> flourFlow) {
        setName("Thread.Mill");

        this.engine = new WaterWheel(waterFlow);
        this.engine.start();

        this.machine = new MillStone(this.engine, milletFlow, flourFlow);
        this.machine.start();
    }

    public boolean isMachineOn() {
        return this.machine.isOn();
    }

    public boolean isEngineOn() {
        return this.engine.getPower() > 0;
    }

    public int power() {
        return this.engine.getPower();
    }

    public void run() {
        while (!isInterrupted()) {
            if (engine.getPower() > 0) {
                machine.on();
            }
        }
    }

    @Override
    public String toString() {
        return "Mill{" +
                "engine=" + engine +
                ", machine=" + machine +
                '}';
    }
}
