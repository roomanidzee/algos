package com.romanidze.algos

import java.util.Scanner

import scala.util.Random
import scala.collection.mutable

object LinearTask extends App {

  def merge(left: List[Int], right: List[Int]): List[Int] =
    (left, right) match {
      case (left, Nil)  => left
      case (Nil, right) => right
      case (leftHead :: leftTail, rightHead :: rightTail) => {
        if (leftHead < rightHead) {
          leftHead :: merge(leftTail, right)
        } else {
          rightHead :: merge(left, rightTail)
        }
      }

    }

  def mergeSort(list: List[Int]): List[Int] = {
    val n = list.length / 2
    if (n == 0) {
      list
    } else {
      val (left, right) = list.splitAt(n)
      merge(mergeSort(left), mergeSort(right))
    }
  }

  var inputList = new mutable.ListBuffer[Int]()
  val scanner   = new Scanner(System.in)
  val rnd       = new Random()

  print("Введите первое число: ")
  val start = scanner.nextInt()
  println("")

  print("Введите второе число: ")
  val end = scanner.nextInt()
  println("")

  require((end > start) && (end > 0 && start >= 0), "Неверный ввод данных")

  (start to end - 2)
    .foreach(_ => inputList += (start + rnd.nextInt((end - start) + 1)))

  println(s"Введённые данные: $inputList")

  inputList = mergeSort(inputList.toList).to[mutable.ListBuffer]

  println(s"Отсортированный массив: $inputList")

  var counter = 0

  (0 until end - 1)
    .foreach(
      i =>
        if (i < inputList(i) && counter < 2) {
          counter += 1
          println(s"Элемент: $i")
        }
    )

}
