package com.example.mill.machine;

public class Machine extends Thread{
    private boolean on;

    public boolean isOn() {
        return on;
    }

    public void on(){
        on = true;
    }

    public void off(){
        on = false;
    }
}
