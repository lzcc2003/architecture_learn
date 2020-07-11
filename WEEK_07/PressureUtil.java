package com.joy.programmer.joyce.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.commons.lang3.time.StopWatch;

public class PressureUtil {
    // 总的请求个数
    public static final int requestTotal = 1000;

    // 同一时刻最大的并发线程的个数
    public static final int concurrentThreadNum = 10;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        StopWatch stopWatch = StopWatch.createStarted();
        CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        Semaphore semaphore = new Semaphore(concurrentThreadNum);
        for (int i = 0; i < requestTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    String result = testRequestUri();
                    //System.out.println("result:{} " + result);
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("exception : " + e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        stopWatch.stop();
        System.out.println("请求完成,time cost="+stopWatch.getTime());

    }

    private static String testRequestUri() {
        return HttpClientUtil.doGet("https://www.baidu.com/");
    }
}