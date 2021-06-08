package example.seventh;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/7 - 16:15
 **/
//继承RecursiveTask来创建可以用于分支/合并框架的任务
public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long>{
    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end -start;
        if (length <= THRESHOLD){
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }
    private long computeSequentially(){
        long sum = 0;
        for(int i = start; i < end; i++){
            sum += numbers[i];
        }
        return sum;


    }


}
