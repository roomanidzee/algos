package com.romanidze.algos.first

import scala.collection.mutable
import scala.util.Random

object MoveElementsTask extends App {

  var inputList = new mutable.ListBuffer[Int]()
  val rnd       = new Random()
  val limit     = 10

  (0 until limit)
    .foreach(_ => inputList += rnd.nextInt(limit))

  println(s"Оригинальный массив: $inputList")

  val tempVar: Int = inputList.head

  for (i <- 0 until limit - 1) {
    inputList(i) = inputList(i + 1)
  }

  inputList(limit - 1) = tempVar

  println(s"Изменённый массив: $inputList")

}
