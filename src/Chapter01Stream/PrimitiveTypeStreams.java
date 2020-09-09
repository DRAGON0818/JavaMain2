package Chapter01Stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveTypeStreams {

    public static void show(String title, IntStream stream) {
        final int SIZE=10;
        int[] firstElements = stream.limit(SIZE + 1).toArray();
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.length; i++) {
            if(i>0) System.out.print(", ");
            if(i<SIZE) System.out.print(firstElements[i]);
            else System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100));
        show("is1",is1);
        IntStream is2 = IntStream.range(5, 10);
        show("is2",is2);
        IntStream is3 = IntStream.rangeClosed(5, 10);
        show("is3",is3);

        Path path = Paths.get("/Users/donglixin/IdeaProjects/JavaMain2/A.txt");
        String contents=new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream is4 = words.mapToInt(String::length);
        show("is4", is4);

        String sentence = "\uD835\uDD46 is the set of octonions.";
        System.out.println(sentence);

        //CharSequence接口的codePoints和chars方法，可以生成由字符的Unicode码或者UTF16编码机制的码元构成的IntStream。
        IntStream codes = sentence.codePoints();
        System.out.println(codes.mapToObj(c -> String.format("%X ", c)).collect(Collectors.joining("，")));

        //基础数据流封装成对象流，需要通过封装 boxed()方法
        Stream<Integer> integers = IntStream.range(0, 100).boxed();
        IntStream is5 = integers.mapToInt(Integer::intValue);
        show("is5",is5);

    }
}
