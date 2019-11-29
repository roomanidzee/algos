package com.romanidze.algos.eighth

import java.util.Scanner

object Tickets extends App{

  print("Введите число: ")
  val scanner = new Scanner(System.in)
  println("")

  val number = scanner.nextLine()

  val chars = number.toCharArray

  var counter1 = 0
  var counter2 = 0

  for(i <- 0 until number.length - 1){
    counter1 += number(i).toInt
    counter2 += number(i + 1).toInt
  }

  if(counter1 != counter2){
    println("Билет не является счастливым")
  }else{
    println("Билет счастливый")
  }

}
