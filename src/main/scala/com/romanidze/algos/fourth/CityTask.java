package com.romanidze.algos.fourth;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 25.10.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class CityTask{

    private static List<String> findLongestChain(Set<String> cities){
        Map<Character, Set<String>> citiesIndex = createIndex(cities);
        return findLongestChain(citiesIndex);
    }

    private static List<String> findLongestChain(Map<Character, Set<String>> citiesIndex){
        List<String> chain = new ArrayList<>(0);

        for (Character firstChar : citiesIndex.keySet()) {
            List<String> newChain = findLongestChain(citiesIndex, firstChar);
            if (chain.size() < newChain.size()) {
                chain = newChain;
            }
        }

        return chain;
    }

    private static List<String> findLongestChain(Map<Character, Set<String>> citiesIndex, Character firstChar){

        List<String> chain = new ArrayList<>(0);

        try {

            for (String city : citiesIndex.get(firstChar)) {

                Map<Character, Set<String>> index = cloneIndex(citiesIndex);
                index.get(firstChar).remove(city);

                List<String> newChain = new ArrayList<>();
                newChain.add(city);
                newChain.addAll(findLongestChain(index, city.toUpperCase().charAt(city.length() - 1)));

                if (chain.size() < newChain.size()) {
                    chain = newChain;
                }

            }

        }
        catch (NullPointerException ignored) {}

        return chain;
    }

    private static Map<Character, Set<String>> cloneIndex(Map<Character, Set<String>> citiesIndex){
        return citiesIndex.keySet()
                          .stream()
                          .collect(
                                  Collectors.toMap(
                                          key -> key,
                                          key -> new HashSet<>(citiesIndex.get(key)),
                                          (a, b) -> b
                                  )
                          );
    }

    private static Map<Character, Set<String>> createIndex(Set<String> cities){
        Map<Character, Set<String>> index = new HashMap<>();

        cities.forEach(city -> {
            Set<String> cs = index.computeIfAbsent(city.charAt(0), k -> new HashSet<>());
            cs.add(city);
        });
        return index;
    }


    public static void main(String[] args) throws IOException{
        final Set<String> cities = new HashSet<>();

        try(BufferedReader in = new BufferedReader(new FileReader("src/main/resources/city_data.txt"))){

            String line = in.readLine();

            while(line != null){
                cities.add(line);
                line = in.readLine();
            }

        }

        List<String> result = findLongestChain(cities);

        System.out.print("Итоговая цепочка: " + result.get(0) + " -> ");

        IntStream.range(1, result.size() - 1)
                 .mapToObj(i -> result.get(i) + " -> ")
                 .forEachOrdered(System.out::print);

        System.out.print(result.get(result.size() - 1));

    }
}
