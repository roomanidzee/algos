package com.romanidze.algos.second

import java.util.Scanner

object LevensteinTask extends App {

  def minNumber(numbers: Int*): Int = numbers.min

  def calculateDistance(first: String, second: String): Int = {

    val rows    = first.length + 1
    val columns = second.length + 1

    val symbolArray = Array.ofDim[Int](rows, columns)

    println("Готовим пустые строки для строк матрицы \n")

    (1 until rows)
      .foreach(i => symbolArray(i)(0) = i)

    println("Готовим пустые строки для колонок матрицы \n")

    (1 until columns)
      .foreach(i => symbolArray(0)(i) = i)

    var cost: Int = 0

    var deleteResult: Int     = 0
    var insertResult: Int     = 0
    var substituteResult: Int = 0

    for (i <- 1 until columns) {

      for (j <- 1 until rows) {

        if (first.charAt(j - 1).equals(second.charAt(i - 1))) {
          cost = 0
        } else {
          cost = 1
        }

        println(s"Стоимость операций Левенштейна: $cost \n")

        println(s"Удаляем ${j - 1} символ из первой строки \n")
        deleteResult = symbolArray(j - 1)(i) + 1

        println(s"Вставляем ${i - 1} символ из второй строки \n")
        insertResult = symbolArray(j)(i - 1) + 1

        println(s"Заменяем ${j - 1} элемент в первой строке и ${i - 1} элемент во второй строке \n")
        substituteResult = symbolArray(j - 1)(i - 1) + cost

        println(s"Общая стоимость: ${deleteResult + insertResult + substituteResult} \n")

        symbolArray(j)(i) = minNumber(deleteResult, insertResult, substituteResult)

      }

    }

    symbolArray(first.length)(second.length)

  }

  val scanner = new Scanner(System.in)

  print("Введите первую строку: ")
  var first = scanner.nextLine()
  println("")

  print("Введите вторую строку: ")
  var second = scanner.nextLine()
  println("")

  val levensteinResult = calculateDistance(first, second)
  println(s"Расстояние Левенштейна: $levensteinResult")

}
