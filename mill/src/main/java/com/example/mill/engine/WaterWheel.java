package com.example.mill.engine;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.mill.bean.Water;

public class WaterWheel extends Engine {
    private final Logger logger = LoggerFactory.getLogger(this.getName());

    private final Queue<Water> waterFlow;

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public static int MAX_POWER = 10;

    public WaterWheel(Queue<Water> fuelFlow) {
        setName("Thread.WaterWheel");
        this.waterFlow = fuelFlow;
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                Water water = waterFlow.poll();
                this.executor.execute(() -> {
                    if (water != null && this.getPower() < MAX_POWER) {
                        this.incPower(water.getPower());
                    }
                });
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
