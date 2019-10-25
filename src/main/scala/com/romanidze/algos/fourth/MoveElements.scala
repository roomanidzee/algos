package com.romanidze.algos.fourth

import scala.collection.mutable
import scala.util.Random

object MoveElements extends App {

  val limit = 10

  var inputList = new mutable.ListBuffer[Int]()
  val rnd       = new Random()

  (0 until limit)
    .foreach(_ => inputList += rnd.nextInt(limit))

  println(s"Оригинальный массив: $inputList")

  for (i <- 0 until inputList.length - 1) {

    inputList(i) -= inputList(i + 1)
    inputList(i + 1) += inputList(i)
    inputList(i) = inputList(i + 1) - inputList(i)

  }

  println(s"Изменённый массив: $inputList")

}
