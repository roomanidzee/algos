package com.romanidze.algos.third.postman

import com.romanidze.algos.third.common.FileHelper
import com.romanidze.algos.third.common.FileHelper.VertexInfo

import scala.collection.mutable.ListBuffer

object PostmanLauncher extends App {

  val filePath = "src/main/resources/postman_data.txt"

  val data: ListBuffer[VertexInfo] = FileHelper.getVertexInfo(filePath)

  val solver = new PostmanHelper(FileHelper.getVerticleCount(filePath))

  data.foreach(elem => {
    solver.addArc(elem.label, elem.start, elem.end, elem.weight)
  })

  solver.solve()
  solver.printCPT(0)

}
