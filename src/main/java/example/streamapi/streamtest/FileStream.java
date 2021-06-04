package example.streamapi.streamtest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/6/4 - 15:19
 **/
public class FileStream {
    long uniqueWords = 0;
        Stream<String> lines;

    {
        try {
            lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset());
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
