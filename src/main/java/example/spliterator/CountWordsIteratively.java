
package example.spliterator;

import org.junit.Test;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.lang.Math;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/7 - 17:04
 **/
public class CountWordsIteratively {
    private int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
    public int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {    //遍历String中的所有字符
            if (Character.isWhitespace(c)){
                lastSpace = true;
            }else{
                if (lastSpace) counter++;   //上一个字符是空格，而当前遍历的字符不是空格是，将单词计数器加一
                lastSpace = false;
            }
        }
        return counter;
    }

    @Test
    public void test(){
        final String SENTENCE = "Nel mezzo del cammin di nostra vita" + "mi ritrovai in una selva osvura" + "che la dritta via era smarrita";
        System.out.println("Found: " + countWordsIteratively(SENTENCE) + "words");

//        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
//        System.out.println("Found:"+ countWords(stream) + "words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found:" + countWords(stream) + "words");




    }



}
