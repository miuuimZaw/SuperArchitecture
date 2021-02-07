package com.zaw.superarch.demo;

import java.time.LocalDateTime;

/**
 * @author zhangaiwen
 */
public class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + LocalDateTime.now());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + LocalDateTime.now());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }

}
