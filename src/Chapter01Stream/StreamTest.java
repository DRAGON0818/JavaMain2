package Chapter01Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        /*List<String> list = new ArrayList<>();
        list.add("tong");
        list.add("niu");
        list.add("fang");
        list.stream().forEach(System.out::println);

        letters("boat").forEach(System.out::println);
        list.stream().flatMap(w->letters(w)).forEach(System.out::println);*/
        //Object[] objects = Stream.iterate(1.0, a -> a * 2).peek(e -> test()).limit(20).toArray();
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale availableLocale : availableLocales) {
            System.out.print(availableLocale);
            System.out.println(" ,"+availableLocale.getLanguage());
            Set<String> singleton = Collections.singleton(availableLocale.getDisplayLanguage());
            System.out.println(singleton);
        }
    }

    public static void test(){
        System.out.println("1");
        System.out.println("2");
    }

    public static Stream<String> letters(String s) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            results.add(s.substring(i, i+1));
        }
        return results.stream();
    }
}
