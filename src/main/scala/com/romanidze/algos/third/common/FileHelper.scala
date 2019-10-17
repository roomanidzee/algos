package com.romanidze.algos.third.common

import java.io.{ FileNotFoundException, IOException }

import scala.collection.mutable.ListBuffer
import scala.io.Source

object FileHelper {

  case class VertexInfo(
    label: String,
    start: Int,
    end: Int,
    weight: Int
  )

  def getVerticleCount(filePath: String): Int = {

    val fileSource = Source.fromFile(filePath)
    val count      = fileSource.getLines.toSeq.head.toInt

    fileSource.close()

    count
  }

  def getVertexInfo(filePath: String): ListBuffer[VertexInfo] = {

    var result = new ListBuffer[VertexInfo]()

    val fileSource = Source.fromFile(filePath)

    try {

      fileSource.getLines
        .drop(1)
        .foreach(line => {

          val data = line.split(" ")

          result += VertexInfo(data(0), data(1).toInt, data(2).toInt, data(3).toInt)

        })

    } catch {
      case _: FileNotFoundException =>
        Console.err.println(s"Не найден файл вершин по пути $filePath")
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
