package com.romanidze.algos

import java.util.Scanner
import scala.collection.mutable.ListBuffer

object StirlingTask extends App{

  def peopleSeparator(people: Int, commands: Int): Int = {

    val limit = people - commands

    var tempArray = List.fill(limit + 1)(1).to[ListBuffer]

    (2 to commands)
      .foreach(i => (1 to limit)
        .foreach(j => tempArray(j) += i * tempArray(j - 1)))

    tempArray(limit)

  }

  val scanner = new Scanner(System.in)

  print("Введите количество команд: ")
  val commands: Int = scanner.nextInt()
  println("")

  print("Введите количество участников: ")
  val people: Int = scanner.nextInt()
  println("")

  val separationResult: Int = peopleSeparator(people, commands)

  println(s"Способов разделения: $separationResult")

}
