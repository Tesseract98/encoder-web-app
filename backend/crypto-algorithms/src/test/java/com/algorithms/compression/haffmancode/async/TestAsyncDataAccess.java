package com.algorithms.compression.haffmancode.async;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.*;

public class TestAsyncDataAccess {

//  Use for checking multithreading environment
    @Ignore("execution time almost 10s")
    @Test
    public void testOnOrderingAndRaceCondition() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // Recursive thread pool
//        ExecutorService executorService = new ForkJoinPool(numberOfThreads);

        StringBuilder encryptedText2 = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            AsyncTest1 asyncTest1 = new AsyncTest1();
            AsyncTest2 asyncTest2 = new AsyncTest2();
            Future<String> future = executorService.submit(asyncTest1);
            Future<String> future2 = executorService.submit(asyncTest2);
            try {
                encryptedText2.append(future.get());
                encryptedText2.append(future2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(encryptedText2.toString());
    }

}
