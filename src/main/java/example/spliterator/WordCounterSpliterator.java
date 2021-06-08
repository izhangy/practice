package example.spliterator;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/7 - 17:42
 **/
public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize<10){
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++){
            if (Character.isWhitespace(string.charAt(splitPos))){
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        return false;
    }

    @Override
    public void forEachRemaining(Consumer<? super Character> action) {

    }

    @Override
    public long getExactSizeIfKnown() {
        return 0;
    }

    @Override
    public boolean hasCharacteristics(int characteristics) {
        return false;
    }

    @Override
    public Comparator<? super Character> getComparator() {
        return null;
    }
}
