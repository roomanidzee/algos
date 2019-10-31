package com.romanidze.algos.fifth.jarvis.helpers;

import com.romanidze.algos.fifth.jarvis.dto.PointDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 31.10.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class FileHelper {

    private static final Path filePath = Path.of("src/main/resources/jarvis_data.txt");

    public static List<PointDTO> getData(){

        List<PointDTO> resultList = null;

        try(BufferedReader br = Files.newBufferedReader(filePath)){

            List<String> fileLines = br.lines()
                    .collect(Collectors.toList());

            resultList = fileLines.stream()
                    .map(item -> {
                        PointDTO point = new PointDTO();

                        point.setX(Integer.valueOf(item.split(" ")[0]));
                        point.setY(Integer.valueOf(item.split(" ")[1]));

                        return point;
                    })
                    .collect(Collectors.toList());

        }catch(IOException e){
            System.err.println("При работе с файлом произошла ошибка: " + e.getMessage());
        }

        return resultList;

    }

}
