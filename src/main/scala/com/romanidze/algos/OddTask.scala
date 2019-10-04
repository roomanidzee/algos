package com.romanidze.algos

import scala.util.Random
import scala.collection.mutable

object OddTask extends App {

  def maxRange(array: List[Int], index: Int): Int = {

    var candidate = index
    val value     = array(index)
    var step      = 1

    while (candidate > 0 && array(candidate) == value) {

      candidate -= step
      step *= 2

    }

    var firstElem  = math.max(0, candidate)
    var secondElem = candidate + (step / 2)
    var tempVar    = 0

    while (firstElem + 1 != secondElem) {

      tempVar = (firstElem + secondElem) / 2

      if (array(tempVar) == value) {
        secondElem = tempVar
      } else {
        firstElem = tempVar
      }

    }

    secondElem

  }

  def search(array: List[Int], start: Int, end: Int): Int = {

    if (array(start) == array(end - 1)) {
      return end
    }

    val middle = (start + end) / 2

    val startOfRange = maxRange(array, middle)

    if ((startOfRange & 1) == 0) {
      search(array, middle, end)
    }

    search(array, start, startOfRange)

  }

  val limit = 99000000

  var inputList = new mutable.ListBuffer[Int]()
  val rnd       = new Random()

  (0 until limit)
    .foreach(_ => inputList += rnd.nextInt(limit))

  val indexOfElem = search(inputList.toList, 0, inputList.length)

  println(s"Элемент: ${inputList(indexOfElem)}")

}
