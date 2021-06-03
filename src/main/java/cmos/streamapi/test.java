package cmos.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

    }
}
