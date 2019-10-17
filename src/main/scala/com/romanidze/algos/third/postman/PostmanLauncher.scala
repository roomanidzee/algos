package com.romanidze.algos.third.postman

import com.romanidze.algos.third.postman.FileHelper.PostmanPath

import scala.collection.mutable.ListBuffer

object PostmanLauncher extends App {

  val data: ListBuffer[PostmanPath] = FileHelper.getPostmanData

  val solver = new PostmanHelper(FileHelper.getVerticleCount)

  data.foreach(elem => {
    solver.addArc(elem.label, elem.start, elem.end, elem.weight)
  })

  solver.solve()
  solver.printCPT(0)

}
