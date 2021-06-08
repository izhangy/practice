package example.streamapi.streamtest;

import com.alibaba.fastjson.JSONObject;

import javax.management.RuntimeMBeanException;
import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/4 - 10:46
 **/
public class StreamTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300, Currency.getInstance(Locale.CHINA)),
                new Transaction(raoul, 2012, 1000, Currency.getInstance(Locale.CANADA)),
                new Transaction(raoul, 2011, 400, Currency.getInstance(Locale.US)),
                new Transaction(mario, 2012, 710, Currency.getInstance(Locale.KOREA)),
                new Transaction(mario, 2012, 700, Currency.getInstance(Locale.CHINA)),
                new Transaction(alan, 2012, 950, Currency.getInstance(Locale.UK))
        );
        List<Transaction> tr2011 = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(tr2011);

        //筛选交易员的工作城市
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities);

        //查找来自于剑桥的交易员
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(traders);

        //返回所有交易员的姓名字符串，按字母排序
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        boolean milanBased = transactions.stream().allMatch(transaction -> transaction.getTrader()
        .getCity().equals("Milan"));
        System.out.println(milanBased);

        //打印生活在剑桥的交易员所有交易额
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        //所有交易中，最高的交易额
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(highestValue);

        //交易额最小的交易
        Optional<Transaction> smallestTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(smallestTransaction);

        Optional<Transaction> minTransaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(minTransaction);


        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
            System.out.println(JSONObject.toJSONString(transactionsForCurrency));
        }

        //Collectors工厂
        //groupingBy将具有相同币种的交易额进行分组
        Map<Currency, List<Transaction>> transactionByCurrencies = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println(JSONObject.toJSONString(transactionByCurrencies));




    }
}
