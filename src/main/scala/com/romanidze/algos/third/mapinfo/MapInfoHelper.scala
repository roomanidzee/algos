package com.romanidze.algos.third.mapinfo

import com.romanidze.algos.third.common.FileHelper.VertexInfo

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class MapInfoHelper(weights: ListBuffer[VertexInfo], vertexNum: Int) {

  def getMedianAndCenter: (Int, Int) = {

    val dist: Array[Array[Double]] =
      Array.tabulate[Double](vertexNum, vertexNum)((_, _) => Double.PositiveInfinity)

    weights
      .foreach(elem => dist(elem.start - 1)(elem.end - 1) = elem.weight)

    val next: Array[Array[Int]] = Array.ofDim[Int](vertexNum, vertexNum)

    next.indices.foreach(i => {

      next.indices.foreach(j => {

        if (i != j) {
          next(i)(j) = j + 1
        }

      })

    })

    for (k <- 0 until vertexNum) {

      for (i <- 0 until vertexNum) {

        for (j <- 0 until vertexNum) {

          if (dist(i)(k) + dist(k)(j) < dist(i)(j)) {

            dist(i)(j) = dist(i)(k) + dist(k)(j)
            next(i)(j) = next(i)(k)

          }

        }

      }

    }

    var vertexMap: mutable.Map[Int, ListBuffer[Int]] = mutable.Map.empty[Int, ListBuffer[Int]]
    var vertex                                       = 0

    next.indices.foreach(i => {
      next.indices.foreach(j => {

        if (i != j) {

          vertex = i + 1

          if (!vertexMap.contains(vertex)) {
            vertexMap += (vertex -> ListBuffer[Int](dist(i)(j).toInt))
          } else {
            var values: ListBuffer[Int] = vertexMap(vertex)
            values += dist(i)(j).toInt
            vertexMap += (vertex -> values)
          }

        }

      })
    })

    //медиана графа

    var medianMap: mutable.Map[Int, Int] = mutable.Map.empty[Int, Int]

    for ((key, value) <- vertexMap) {
      medianMap += (key -> value.sum)
    }

    val sortedMedianMap = mutable.ListMap(medianMap.toSeq.sortWith(_._2 < _._2): _*)

    //центр графа

    var centerMap: mutable.Map[Int, Int] = mutable.Map.empty[Int, Int]

    for ((key, value) <- vertexMap) {
      centerMap += (key -> value.max)
    }

    val sortedCenterMap = mutable.ListMap(centerMap.toSeq.sortWith(_._2 < _._2): _*)

    (sortedMedianMap.head._1, sortedCenterMap.head._1)

  }

}
