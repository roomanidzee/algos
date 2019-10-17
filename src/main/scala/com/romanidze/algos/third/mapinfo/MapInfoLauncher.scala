package com.romanidze.algos.third.mapinfo

import com.romanidze.algos.third.common.FileHelper
import com.romanidze.algos.third.common.FileHelper.VertexInfo

import scala.collection.mutable.ListBuffer

object MapInfoLauncher extends App {

  val filePath = "src/main/resources/mapinfo_data.txt"

  val data: ListBuffer[VertexInfo] = FileHelper.getVertexInfo(filePath)

  val solver = new MapInfoHelper(data, FileHelper.getVerticleCount(filePath))

  val result = solver.getMedianAndCenter

  println(s"Медиана графа: вершина ${result._1}, центр графа: вершина ${result._2}")

}
