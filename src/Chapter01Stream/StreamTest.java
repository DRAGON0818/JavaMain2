package Chapter01Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("tong");
        list.add("niu");
        list.add("fang");
        list.stream().forEach(System.out::println);

        letters("boat").forEach(System.out::println);
        list.stream().flatMap(w->letters(w)).forEach(System.out::println);
    }

    public static Stream<String> letters(String s) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            results.add(s.substring(i, i+1));
        }
        return results.stream();
    }
}
