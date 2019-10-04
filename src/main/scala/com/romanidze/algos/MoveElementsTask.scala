package com.romanidze.algos

import scala.util.Random
import scala.collection.mutable

object MoveElementsTask extends App{

  var inputList = new mutable.ListBuffer[Int]()
  val rnd = new Random()
  val limit = 10

  (0 until limit)
    .foreach(_ => inputList += rnd.nextInt(limit))

  println(s"Оригинальный массив: $inputList")

  val tempVar: Int = inputList.head

  for(i <- 0 to limit - 2){

    inputList(i) = inputList(i + 1)

    if(i == limit - 2){
      inputList(i - 1) = tempVar
    }

  }

  println(s"Изменённый массив: $inputList")

}
