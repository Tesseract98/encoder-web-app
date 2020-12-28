package com.algorithms.compression.haffmancode.async;

import java.util.concurrent.Callable;

public class AsyncTest1 implements Callable<String> {

    public static int counter = 0;

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        counter++;
        return " sleeped " + counter + " "  + Thread.currentThread() + "; ";
    }

}
