package com.romanidze.algos.sixth.huffman;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 07.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class HuffmanLauncher {

    public static void main(String[] args) {

        Path path = Path.of("src/main/resources/huffman_data.txt");

        try(BufferedReader br = Files.newBufferedReader(path)){

            List<String> stringsList = br.lines()
                                         .map(HuffmanHelper::clearString)
                                         .collect(Collectors.toList());

            Map<Character, Integer> charFreq = new HashMap<>();

            stringsList.forEach(item ->
                    IntStream.range(0, item.length())
                             .forEachOrdered(i ->
                                  charFreq.put(item.charAt(i),
                                  !charFreq.containsKey(item.charAt(i)) ? 1 : charFreq.get(item.charAt(i)) + 1
                             )
                            ));

            List<Character> characters = new ArrayList<>(charFreq.keySet());
            List<Integer> frequencies = new ArrayList<>(charFreq.values());

            int numberOfCharacters = characters.size();

            Comparator<HuffmanNode> comparable = Comparator.comparingInt(HuffmanNode::getFrequency);
            PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(numberOfCharacters, comparable);

            IntStream.range(0, numberOfCharacters)
                     .mapToObj(i -> {
                         HuffmanNode node = new HuffmanNode();
                         node.setCharacter(characters.get(i));
                         node.setFrequency(frequencies.get(i));
                         node.setLeft(null);
                         node.setRight(null);

                         return node;
                     })
                     .forEachOrdered(priorityQueue::add);

            HuffmanNode root = null;

            while(priorityQueue.size() > 1){

                HuffmanNode firstMinimum = priorityQueue.peek();
                priorityQueue.poll();

                HuffmanNode secondMinimum = priorityQueue.peek();
                priorityQueue.poll();

                HuffmanNode equalityNode = new HuffmanNode();

                System.out.println("Узел в дереве: " + equalityNode.toString());

                equalityNode.setFrequency(firstMinimum.getFrequency() + secondMinimum.getFrequency());
                equalityNode.setCharacter('-');

                equalityNode.setLeft(firstMinimum);
                equalityNode.setRight(secondMinimum);

                root = equalityNode;

                priorityQueue.add(equalityNode);

            }

            String characterString = characters.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining());

            System.out.println("Исходная строка: " + characterString);

            Map<Character, String> encodeMap = new HashMap<>();

            HuffmanHelper.prepareString(encodeMap, root, "");

            String encodedString = IntStream.range(0, characterString.length())
                    .mapToObj(i -> encodeMap.get(characterString.charAt(i)))
                    .collect(Collectors.joining());

            System.out.println(" ");
            System.out.println("Размер исходного файла в байтах: " + path.toFile().length());
            System.out.println("Размер после сжатия в байтах: " + HuffmanHelper.stringSize(encodedString));

        }catch(IOException e){
            System.out.println("Произошла ошибка при чтении из файла, причина: " + e.getMessage());
        }


    }

}
