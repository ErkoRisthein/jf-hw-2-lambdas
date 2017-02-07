package org.zeroturnaround.jf.homework2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toMap;

public class Homework2 {

  public Map<String, Long> getWords(String filename) throws IOException {
    return getWords(Files.lines(Paths.get(filename)));
  }

  Map<String, Long> getWords(Stream<String> lines) {
    return lines
            .map(line -> line.split("[\\p{Punct}\\s]+"))
            .flatMap(Arrays::stream)
            .filter(word -> word.length() > 1)
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(word -> word, counting()))
            .entrySet()
            .stream()
            .sorted(comparingByValue(reverseOrder()))
            .limit(5)
            .collect(toMap(Entry::getKey, Entry::getValue));
  }

}
