package example.streamapi.streamtest;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/4 - 10:38
 **/
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + this.trader + ", " +
                "year:" + this.year + ","
                + "value:" + this.value + "}";
    }
}
