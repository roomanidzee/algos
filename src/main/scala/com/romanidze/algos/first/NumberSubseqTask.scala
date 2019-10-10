package com.romanidze.algos.first

import scala.collection.mutable.ListBuffer

object NumberSubseqTask extends App {

  val testList = List(3, 29, 5, 5, 28, 6)

  println(s"Оригинальный массив: $testList")
  println(" ")

  var tempList = List.fill(testList.length)(0).to[ListBuffer]

  for (i <- testList.indices) {

    for (j <- 0 until i) {

      if (testList(i) > testList(j) && tempList(j) > tempList(i)) {

        tempList(i) = tempList(j)

      }

    }

    tempList(i) += 1

  }

  val last  = tempList.max
  var index = tempList.indexOf(last)

  var j                       = 0
  var result: ListBuffer[Int] = ListBuffer(testList(index))

  while (tempList(index) != -1 && index > 0) {

    j = index - 1

    while ((tempList(j) != tempList(index) - 1 || testList(j) >= testList(index)) && (j > 0)) {
      j -= 1
    }

    index = j
    result += testList(index)

  }

  var finalResult = result.reverse

  if (finalResult(0) > finalResult(1)) {
    finalResult.remove(0)
  }

  println(s"Подпоследовательность: ${finalResult}")

}
