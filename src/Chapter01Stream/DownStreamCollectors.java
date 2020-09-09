package Chapter01Stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

public class DownStreamCollectors {
    public static class City{
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }
    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename)).map(l -> l.split(",")).
                map(a->new City(a[0],a[1],Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(groupingBy(Locale::getCountry, toSet()));
        System.out.println("countryToLocaleSet:" + countryToLocaleSet);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locales.collect(groupingBy(Locale::getCountry, counting()));
        System.out.println("countryToLocaleCounts:" + countryToLocaleCounts);

        Stream<City> cities = readCities("/Users/donglixin/Downloads/corejava/v2ch01/cities.txt");
        Map<String, Optional<City>> stateLargestCity = cities.collect(
                groupingBy(City::getState, maxBy(Comparator.comparing(City::getPopulation)))
        );
        stateLargestCity.forEach((x,y)->{
            System.out.println(x+" "+y.get().getName());
        });

        cities = readCities("/Users/donglixin/Downloads/corejava/v2ch01/cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(groupingBy(
                City::getState, mapping(City::getName, maxBy(Comparator.comparing(String::length)))
        ));
        System.out.println("@@@@@"+stateToLongestCityName);

        Stream<Locale> l = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = l.collect(
                groupingBy(Locale::getDisplayCountry,
                        mapping(Locale::getDisplayLanguage, toSet()))
        );
        System.out.println("在"+countryToLanguages);

        cities = readCities("/Users/donglixin/Downloads/corejava/v2ch01/cities.txt");
        Map<String, String> stateToCityName = cities.collect(groupingBy(
                City::getState,
                reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + "," + t)
        ));
        System.out.println("stateToCityNames:"+stateToCityName);

    }
}
