package example.streamapi;

import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.uitoolkit.ui.DialogHook;
import example.streamapi.streamtest.CaloricLevel;

import javax.swing.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/2 - 17:19
 **/
public class test {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 500, Dish.Type.OTHER),
                new Dish("pizza", true, 500, Dish.Type.OTHER),
                new Dish("pizza", true, 500, Dish.Type.OTHER),
                new Dish("pizza", true, 500, Dish.Type.OTHER),
                new Dish("pizza", true, 500, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        List<String> names = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()){
            Dish d = iterator.next();
            names.add(d.getName());
        }

        //filter接受lambda，从流中排除某些元素。
        //map接受一个lambda，将元素转换为其他形式或提取信息。Dish::getName相当于lambda d->d.getName()
        //limit截断流，使其元素不超过给定的数量
        //collect将流转为其他形式，本例中流被转为列表形式
        List<String> threeHighCaloricDishNames = menu.stream()//从菜单获得流
                .filter(
                d -> d.getCalories() > 300
        ).map(Dish::getName)
                //filter、map、和limit可以连成一条流水线
                .limit(3)//截短流，仅输出前3个元素
                .skip(2)//跳过流，跳过前2个元素
                .distinct()//输出不重复的元素
                .collect(Collectors.toList());//collect将Stream转换为List触发流执行，并关闭。
        System.out.println(threeHighCaloricDishNames);

        //筛选出是蔬菜的列表
        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(vegetarianMenu));

        //筛选出前两个荤菜
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(dishes);

        //映射
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLength = words.stream().map(String::length).collect(Collectors.toList());
        List<Stream<String>> uniqueCharacters = words.stream()
                .map(word->word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(wordLength+""+uniqueCharacters);

        //flatMap方法把一个流中的每个值都换成另一个流，然后把所有流连接起来成一个流
        List<String> uniqueCharacterFlatMap = words.stream()
                .map(w->w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("使用flatMap将各个数组分别映射为一个流: " + uniqueCharacterFlatMap);

        //给定[1,2,3,4]返回[1,4,9,16]
        List<Integer> numbers = Arrays.asList(1,2,3,4,9);
        List<Integer> squares = numbers.stream()
                .map(n -> n*n)
                .collect(Collectors.toList());
        System.out.println(squares);

        //flatMap返回所有的数对
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);
        List<int[]> pairs = nums1.stream()
                .flatMap(i -> nums2.stream()
                        .filter(j -> (i+j) % 3 == 0)//筛选和能被3整除的数组
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        System.out.println(pairs);

        //查找和匹配
        //allMatch（匹配所有元素）、anyMatch（至少匹配一个元素）、noneMatch（确保没有任何元素匹配）
        //allMatch、anyMatch、noneMatch用到了短路运算
        // findFirst（查找第一个元素）和findAny（返回当前流任意元素）

        //Optional<T>是一个容器，代表一个值存在或者不存在。
        //isPresent()将在Optional包含值的时候返回true，否则返回false
        //ifPresent(Consumer<T> block)会在值存在的时候执行给定的代码块。
        //T get() 会在值存在时返回值，否则抛出一个NoSuchElement异常
        //T orElse(T other)会在值存在时返回值，否则返回一个默认值
        menu.stream().filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        //数值流
        //求和
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(calories);

        //映射到数值流
        //mapToInt、mapToDouble、mapToLong
        int caloriesMapToInt = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();//map转化为int进行求和
        System.out.println(caloriesMapToInt);

        //求平均數
        OptionalDouble average = menu.stream()
                .mapToDouble(Dish::getCalories)
                .average();
        System.out.println(average);

        //boxed方法
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();//将数值流转换为Stream

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1,100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                .mapToObj(b -> new int[] {a, b, (int)Math.sqrt(a*a+b*b)}));
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                .mapToObj(
                        b -> new double[] {a, b, Math.sqrt(a*a + b*b)})
                .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples2.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        //Collectors工厂
        //计算有多少种菜
        long howManyDishes = menu.stream().collect(Collectors.counting());
        System.out.println(howManyDishes);

        //查找流中的最大和最小值
        //Collectors.maxBy   Collectors.minBy
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println(mostCaloriesDish);

        //Collectors.summingInt  //把对象求和，并返回一个收集器
        //Collectors.averagingInt、Collectors.averagingLong、Collectors.averagingDouble求平均
        //Collectors.summarizingInt一次操作，可以返回所有的总和、平均值、最大值和最小值。
        IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        //joining工厂返回对每一个对象应用toString方法得到的所有字符串连接成一个字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(shortMenu);
        //joining方法可以重载版本接收元素之间的分界符
        String shortMenu1 = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu1);

        //计算热量总量
        //Collectors.reducing包含三个参数：1.归约的起始值。2.将菜肴转换成一个表示其所含热量的int。3.将两个项目累计成同一个类的值
        int totalCalories = menu.stream().collect(Collectors.reducing(0,Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories);

        int totalCalor = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCalor);

        //分组；按食物类型进行分组
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(JSONObject.toJSONString(dishesByType));

        //各种食物热量最高的食物,先分组后筛选最大热量值
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        //把收集器的结果转换为另一种类型
        Map<Dish.Type, Dish> mostCaloric = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get)));
        System.out.println(mostCaloric);

        //与mapping联合使用
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT; }, Collectors.toSet())));
        System.out.println(caloricLevelsByType);

        //分区
        //找到素食和非素食中热量高的食物
        long start = System.currentTimeMillis();
        long startTime = System.nanoTime();
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
        long duration = System.currentTimeMillis();
        long endTime = System.nanoTime();
        System.out.println("result: " + (duration-start));
        System.out.println(mostCaloricPartitionedByVegetarian);
    }
}
