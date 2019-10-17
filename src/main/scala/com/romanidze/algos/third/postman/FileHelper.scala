package com.romanidze.algos.third.postman

import java.io.{ FileNotFoundException, IOException }

import scala.collection.mutable.ListBuffer
import scala.io.Source

object FileHelper {

  val filePath = "src/main/resources/postman_data.txt"

  case class PostmanPath(
    label: String,
    start: Int,
    end: Int,
    weight: Int
  )

  def getVerticleCount: Int = {

    val fileSource = Source.fromFile(filePath)
    val count      = fileSource.getLines.toSeq.head.toInt

    fileSource.close()

    count
  }

  def getPostmanData: ListBuffer[PostmanPath] = {

    var result = new ListBuffer[PostmanPath]()

    val fileSource = Source.fromFile(filePath)

    try {

      fileSource.getLines
        .drop(1)
        .foreach(line => {

          val data = line.split(" ")

          result += PostmanPath(data(0), data(1).toInt, data(2).toInt, data(3).toInt)

        })

    } catch {
      case _: FileNotFoundException =>
        Console.err.println(s"Не найден файл почтальона по пути $filePath")
      case e: IOException =>
        Console.err.println(
          s"Произошла ошибка при работа с файлом по пути $filePath: ${e.getMessage}"
        )
    } finally {
      fileSource.close()
    }

    result

  }

}
