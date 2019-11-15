package com.romanidze.algos.seventh

import scala.collection.mutable
import scala.util.Random

case class Participant(id: Int, objectID: Int)

object SeventhTask extends App {

  val limit = 10

  val participantList: mutable.ListBuffer[Participant] = mutable.ListBuffer.empty[Participant]
  val rnd                                              = new Random()

  (0 until limit).foreach(i => {
    participantList += Participant(i, rnd.nextInt(limit))
  })

  println(s"Участники с карточками: $participantList")
  println(" ")

  (1 until 6).foreach(i => {

    println(s"Попытка № $i")
    val numbers         = (0 until limit).toList.to[mutable.ListBuffer]

    for (j <- numbers.indices) {

      val randomIndex = rnd.nextInt(numbers.length)
      val randomCard  = numbers(randomIndex)
      numbers.remove(randomIndex)
      println(s"Участнику № $j выпала карточка $randomCard")

    }
    println(" ")

  })

}
