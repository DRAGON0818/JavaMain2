package Chapter01Stream;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingIntoMaps {
    public static class Person{
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id=id;
            this.name = name;
        }

        public int getId() {
            return id;
        }



        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static Stream<Person> people(){
        return Stream.of(new Person(1001, "Peter"), new Person(1002, "Paul"), new Person(1003, "Mary"),new Person(1004,"Jason"));
    }

    public static void main(String[] args) {
        Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println("idToName: " + idToName);

        Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println("idToPerson: " + idToPerson.getClass().getName()+idToPerson);

        idToPerson=people().collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> {
            System.out.println("throw a new IllegalStateExction");
            throw new IllegalStateException();
        }, TreeMap::new));
        System.out.println("idToPerson : " + idToPerson.getClass().getName() + idToPerson);

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(Collectors.toMap(Locale::getDisplayLanguage,
                l -> l.getDisplayLanguage(l),
                (a, b) -> a));
        System.out.println("languageNames:" + languageNames);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguages = locales.collect(Collectors.toMap(Locale::getDisplayCountry, l -> Collections.singleton(l.getDisplayLanguage()),
                (a, b) -> {
                    Set<String> union = new HashSet<>(a);
                    union.addAll(b);
                    return union;
                }));
        System.out.println("countryLanguageSets :"+ countryLanguages);
        Locale l=Locale.getDefault();
        System.out.println(l.getDisplayCountry(l));

        System.out.println("西班牙的语言:"+ countryLanguages.get("西班牙"));

        Map<String, List<Locale>> countryToLocales = Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingBy(Locale::getCountry));
        List<Locale> swillLocales = countryToLocales.get("CH");
        swillLocales.forEach(System.out::println);

        Map<Boolean, List<Locale>> englishAndOthersLocales = Stream.of(Locale.getAvailableLocales()).collect(Collectors.partitioningBy(x -> x.getLanguage().equals("en")));
        List<Locale> englishLocales = englishAndOthersLocales.get(true);
        englishLocales.forEach(System.out::println);

        ConcurrentMap<Boolean, List<Locale>> en = Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingByConcurrent(x -> x.getLanguage().contains("en")));
        List<Locale> locales1 = en.get(true);
        for (Locale locale : locales1) {
            System.out.print(locale+" ,");
        }
    }
}
