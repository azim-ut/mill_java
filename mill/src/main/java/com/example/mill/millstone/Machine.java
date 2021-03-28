package com.example.mill.millstone;

public abstract class Machine extends Thread {
    private boolean on = false;

    public boolean isOn() {
        return on;
    }

    public void on(){ on = true;}
    public void off(){ on = false;
        System.out.println(this.getName() + " stopped");
    }
}
