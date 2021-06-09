package example.eighth;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/9 - 14:33
 **/
public class Debugger {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2,3,4,5);
        numbers.stream().map(x -> x+17)
                .filter(x -> x%2==0)
                .limit(3)
                .forEach(System.out::println);//一旦调用了forEach整个流就会恢复运行

        //peek在每个元素恢复运行之前加入，显示每一个流的输出
        List<Integer> result = numbers.stream()
                .peek(x -> System.out.println("from stream:" + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map:" + x))
                .filter(x->x % 2==0)
                .peek(x -> System.out.println("after filter:" + x))
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x))
                .collect(Collectors.toList());

        numbers.sort(Comparator.naturalOrder());//sort是List接口的默认方法
    }
}
