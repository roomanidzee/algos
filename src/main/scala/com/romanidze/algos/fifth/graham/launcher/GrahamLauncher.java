package com.romanidze.algos.fifth.graham.launcher;

import com.romanidze.algos.fifth.graham.dto.PointDTO;
import com.romanidze.algos.fifth.graham.helpers.FileHelper;
import com.romanidze.algos.fifth.graham.helpers.GrahamHelper;

import java.util.List;

/**
 * 31.10.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class GrahamLauncher {

    public static void main(String[] args) {

        List<PointDTO> points = FileHelper.getData();
        GrahamHelper.computeGraham(points.toArray(new PointDTO[0]));

    }

}
