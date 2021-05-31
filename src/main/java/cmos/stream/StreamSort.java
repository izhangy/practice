package cmos.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/5/19 - 10:04
 **/
//org.apache.commons.beanutils.BeanUtils包中的Beanu.copyProperties是将第二个参数复制给第一个参数
public class StreamSort {
    public static void main(String[] args) {
        BeanObj obj1 = new BeanObj();
        obj1.setActPrio("1");
        BeanObj obj2 = new BeanObj();
        obj2.setActPrio("21");
        BeanObj obj3 = new BeanObj();
        obj3.setActPrio("14");
        BeanObj obj4 = new BeanObj();
        obj4.setActPrio("201");
        BeanObj obj5 = new BeanObj();
        obj5.setActPrio("122");
        BeanObj obj6 = new BeanObj();
        obj6.setActPrio("10");
        List<BeanObj> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        list.add(obj5);
        list.add(obj6);
        List<BeanObj> objsList = new ArrayList<>();
        list.stream().filter(Objects::nonNull).forEach(
                b -> {
                    try {
                        BeanObj obj = new BeanObj();
                        BeanUtils.copyProperties(b, obj);
                        if (b.getActPrio() != null) {
                            Integer priority;
                            priority = Integer.parseInt(b.getActPrio());
                            obj.setPriority(priority);
                        }
                        objsList.add(obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
       List<BeanObj> beanObjList  = objsList.stream().sorted(Comparator.comparing(BeanObj::getPriority,
               Comparator.nullsFirst(Integer::compareTo).reversed())).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(beanObjList));

    }
}
