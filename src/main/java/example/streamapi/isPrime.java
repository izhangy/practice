package example.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/7 - 11:21
 **/
public class isPrime {
    public boolean isPrime(int candidate){
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }


}
