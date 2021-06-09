package example.seventh;

import java.util.function.Function;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/7 - 15:38
 **/
public class MeasureSumPerf {
    public long measureSumPerf(Function<Long, Long> adder, long n){
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }


}

