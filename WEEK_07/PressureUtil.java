import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.alibaba.fastjson.JSON;

import com.google.common.primitives.Ints;
import edu.emory.mathcs.backport.java.util.Collections;
import org.apache.commons.lang3.time.StopWatch;

public class PressureUtil {
    // 总的请求个数
    public static final int requestTotal = 1000;

    // 同一时刻最大的并发线程的个数
    public static final int concurrentThreadNum = 10;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(concurrentThreadNum);
        List<CompletableFuture<Long>> futureList = IntStream.range(0, requestTotal).boxed().map((i) -> CompletableFuture.supplyAsync(() -> {
            try {
                semaphore.acquireUninterruptibly();
                long startTime = System.currentTimeMillis();
                testRequestUri();
                long cost = System.currentTimeMillis() - startTime;
                return cost;
            } finally {
                semaphore.release();
            }

        }, executorService)).collect(Collectors.toList());

        List<Long> results = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        Collections.sort(results);
        System.out.println(JSON.toJSONString(results)+"--"+results.size());
        //tp95
        /**
         * tp95cost:51.874736842105264
         */
        double percent = 0.95;
        int index = (int) (requestTotal * percent);
        OptionalDouble average = IntStream.range(0,index).boxed().mapToLong((i)->
                results.get(i)).average();
        System.out.println(average.getAsDouble());
        executorService.shutdown();
    }

    private static String testRequestUri() {
        return HttpClientUtil.doGet("https://www.baidu.com/");
    }
