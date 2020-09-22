package Chapter01Stream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ParallelStreams {

    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get(
                "/Users/donglixin/IdeaProjects/JavaMain2/A.txt")), StandardCharsets.UTF_8);
        List<String> wordLists = Arrays.asList(contents.split("\\PL+"));
        int[] shortWords = new int[10];
        System.out.println(Arrays.toString(shortWords));
        wordLists.parallelStream().forEach(s->
        {
            if(s.length()<10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));

        Arrays.fill(shortWords,0);
        wordLists.parallelStream().forEach(x->{
            if(x.length()<10) shortWords[x.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));

        Map<Integer, Long> shortWordCounts = wordLists.parallelStream().filter(
                s -> s.length() < 10).collect(groupingBy(String::length,
                counting()));
        System.out.println(shortWordCounts);

        //DownStream order not deterministic   下游顺序不确定性
        Map<Integer, List<String>> result =
                wordLists.parallelStream().collect(groupingByConcurrent(String::length));
        System.out.println(result.get(8));

        result = wordLists.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length)
        );
        System.out.println(result.get(16));

        Map<Integer, Long> wordCounts =
                wordLists.parallelStream().collect(groupingByConcurrent(String::length, counting()));
        System.out.println(wordCounts);

    }

}
