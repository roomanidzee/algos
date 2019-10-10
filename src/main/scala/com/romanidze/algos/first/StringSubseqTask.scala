package com.romanidze.algos.first

import java.util.Scanner

import scala.collection.mutable

object StringSubseqTask extends App {

  def getSequence(first: String, second: String): String = {

    val rows    = first.length + 1
    val columns = second.length + 1

    val symbolArray = Array.ofDim[Int](rows, columns)

    (0 until rows)
      .foreach(
        i =>
          (0 until columns)
            .foreach(j => symbolArray(i)(j) = 0)
      )

    (first.length - 1 to 0 by -1)
      .foreach(
        i =>
          (second.length - 1 to 0 by -1)
            .foreach(
              j =>
                if (first.charAt(i).equals(second.charAt(j))) {
                  symbolArray(i)(j) = 1 + symbolArray(i + 1)(j + 1)
                } else {
                  symbolArray(i)(j) = math.max(symbolArray(i + 1)(j), symbolArray(i)(j + 1))
                }
            )
      )

    val result = new mutable.StringBuilder()
    var i      = 0
    var j      = 0

    while ((symbolArray(i)(j) != 0) && (i < first.length) && (j < second.length)) {

      if (first.charAt(i).equals(second.charAt(j))) {
        result.append(first.charAt(i))
        i += 1
        j += 1
      } else {

        if (symbolArray(i)(j) == symbolArray(i + 1)(j)) {
          i += 1
        } else {
          j += 1
        }

      }

    }

    result.toString()

  }

  val scanner = new Scanner(System.in)

  print("Введите первую строку: ")
  var first = scanner.nextLine()
  println("")

  print("Введите вторую строку: ")
  var second = scanner.nextLine()
  println("")

  val longestSubsequence = getSequence(first, second)

  println(s"Длинная подстрока: $longestSubsequence")

}
