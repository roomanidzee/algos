package com.romanidze.algos.nineth.salesman;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 13.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class Loader {

    public static List<Point2D> prepareData(String filename){

        List<Point2D> result = new ArrayList<>();
        BufferedReader br = null;

        try {
            String currentLine;
            int dimension = 0;
            boolean readingNodes = false;

            br = new BufferedReader(new FileReader(filename));

            while ((currentLine = br.readLine()) != null) {

                if (currentLine.contains("EOF")) {

                    readingNodes = false;

                    if (result.size() != dimension) {

                        System.out.println("Не получилось загрузить города");
                        System.exit(-1);
                    }
                }
                if (readingNodes) {

                    String[] tokens = currentLine.split(" ");

                    float x = Float.parseFloat(tokens[1].trim());
                    float y = Float.parseFloat(tokens[2].trim());

                    Point2D city = new Point2D.Float(x, y);

                    result.add(city);
                }
                if (currentLine.contains("DIMENSION")) {

                    String[] tokens = currentLine.split(":");
                    dimension = Integer.parseInt(tokens[1].trim());
                }
                if (currentLine.contains("NODE_COORD_SECTION")) {
                    readingNodes = true;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;

    }

}
