package example.streamapi.streamtest;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/4 - 10:38
 **/

//final一旦被初始化后就不能再被赋值了
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }


    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
