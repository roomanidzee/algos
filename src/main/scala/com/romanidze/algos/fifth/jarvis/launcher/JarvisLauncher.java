package com.romanidze.algos.fifth.jarvis.launcher;

import com.romanidze.algos.fifth.jarvis.dto.PointDTO;
import com.romanidze.algos.fifth.jarvis.helpers.FileHelper;
import com.romanidze.algos.fifth.jarvis.helpers.JarvisHelper;

import java.util.List;

/**
 * 31.10.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class JarvisLauncher {

    public static void main(String[] args) {

        List<PointDTO> points = FileHelper.getData();

        JarvisHelper jarvisHelper = new JarvisHelper(points.toArray(new PointDTO[0]));

        jarvisHelper.computeJarvis();

    }

}
