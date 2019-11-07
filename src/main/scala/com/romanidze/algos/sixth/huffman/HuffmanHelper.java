package com.romanidze.algos.sixth.huffman;

import java.util.Map;

/**
 * 07.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class HuffmanHelper {

    public static int stringSize(String input){

        return input.codePoints()
                    .map(cp ->
                            cp <= 0x7ff ? cp <= 0x7f ? 1 : 2 : cp <=0xffff ? 3 : 4)
                    .sum();

    }

    public static void prepareString(Map<Character, String> encodeMap, HuffmanNode root, String s){

        System.out.println("ROOT: " + root);

        if(root == null){
            return;
        }

        if (root.getLeft() == null && root.getRight() == null && Character.isLetter(root.getCharacter())) {

            encodeMap.put(root.getCharacter(), s);
            return;
        }

        System.out.println("Строка таблицы кодирования: " + encodeMap);

        prepareString(encodeMap, root.getLeft(), s + "0");
        prepareString(encodeMap, root.getRight(), s + "1");

    }

    public static String clearString(String input){
        return input.replaceAll("[^а-яА-Я]+", " ").trim();
    }

}
