package com.example.mill.millstone;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.mill.bean.Flour;
import com.example.mill.bean.Millet;
import com.example.mill.engine.Engine;

public class MillStone extends Machine {
    private final Logger logger = LoggerFactory.getLogger(this.getName());

    private final ExecutorService executor = Executors.newFixedThreadPool(1);
    private final Queue<Millet> mallets;
    private final Queue<Flour> floursQueue;
    private final Engine engine;

    public MillStone(Engine engine, Queue<Millet> millet, Queue<Flour> flour) {
        setName("Thread.MillStone");
        this.mallets = millet;
        this.floursQueue = flour;
        this.engine = engine;
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                if (engine.getPower() > 0) {
                    engine.decPower(1);

                    this.executor.submit(() -> {
                        try {
                            Millet millet = mallets.poll();
                            if (millet != null) {
                                floursQueue.offer(new Flour());
                                System.out.print("Flour: " + floursQueue.size() + " ");
                            }
                            System.out.println("Power left: " + engine.getPower());
                        } catch (IllegalStateException e) {
                            logger.info("Flour tank is full: " + e.getMessage());
                        }
                    });
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
