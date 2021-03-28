package com.example.mill;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.mill.bean.Flour;
import com.example.mill.bean.MillState;
import com.example.mill.bean.Millet;
import com.example.mill.bean.Water;

@Service
public class MillService {
    private static final BlockingQueue<Water> waterFlow = new ArrayBlockingQueue<>(10);
    private static final BlockingQueue<Millet> milletFlow = new ArrayBlockingQueue<>(10);
    private static final BlockingQueue<Flour> flourFlow = new ArrayBlockingQueue<>(10);
    private Mill mill;
    private final ExecutorService waterPipeExecutor = Executors.newSingleThreadExecutor();
    private final ExecutorService milletPipeExecutor = Executors.newSingleThreadExecutor();

    private final Logger logger = LoggerFactory.getLogger(MillService.class);

    public MillService() {
        mill = new Mill(waterFlow, milletFlow, flourFlow);
        mill.start();
    }

    public MillState getState() {
        return new MillState(mill, waterFlow, milletFlow, flourFlow);
    }

    public void addWater(int capacity) {
        waterPipeExecutor.execute(() -> {
            try {
                int counter = capacity;
                while (counter-- > 0) {
                    waterFlow.add(new Water());
                    Thread.sleep(1);
                }
            } catch (IllegalStateException | InterruptedException e) {
                logger.error("Water tank is full: " + e.getMessage());
            }
        });
    }

    public void dropFlour(){
        flourFlow.clear();
    }

    public void addMillet(int capacity) {
        milletPipeExecutor.execute(() -> {
            try {
                int counter = capacity;
                while (counter-- > 0) {
                    milletFlow.add(new Millet());
                    Thread.sleep(1);
                }
            } catch (IllegalStateException | InterruptedException e) {
                logger.error("Millet tank is full: " + e.getMessage());
            }
        });
    }
}
