package com.algorithms.compression.haffmancode.async;

import java.util.concurrent.Callable;

public class AsyncTest2 implements Callable<String> {

    public static int counter = 0;

    @Override
    public String call() throws Exception {
        counter++;
        return " not sleeped " + counter + " " + Thread.currentThread() + ";";
    }

}
