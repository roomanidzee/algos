package com.romanidze.algos.fourth

import scala.collection.mutable

object BackpackTask extends App {

  case class Item(weight: Int, worth: Int)

  val items = List(
    Item(5, 4),
    Item(4, 3),
    Item(2, 3),
    Item(1, 2)
  )

  var weightLimit = 6

  def prepare(itemIndex: Int, weightLimit: Int): Int = {

    if (itemIndex == 0) {
      0
    } else if (items(itemIndex - 1).weight > weightLimit) {
      prepare(itemIndex - 1, weightLimit)
    } else {
      math.max(
        prepare(itemIndex - 1, weightLimit),
        prepare(itemIndex - 1, weightLimit - items(itemIndex - 1).weight) + items(itemIndex - 1).worth
      )
    }

  }

  var result: mutable.ListBuffer[Item] = mutable.ListBuffer.empty

  for (i <- items.length - 1 to 0 by -1) {

    if (prepare(i + 1, weightLimit) > prepare(i, weightLimit)) {
      result += items(i)
      weightLimit -= items(i).weight
    }

  }

  println(s"Элементы в рюкзаке: $result")

}
