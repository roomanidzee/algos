package com.romanidze.algos.third.postman

import scala.util.control.Breaks._

class PostmanHelper {

  private var verticesCount: Int = 0

  private var verticleDeltas: Array[Int]     = _
  private var negativeVerticles: Array[Int]  = _
  private var postitiveVerticles: Array[Int] = _

  private var matrix: Array[Array[Int]]                  = _
  private var labelsOfArcs: Array[Array[Vector[String]]] = _
  private var duplicatedArcs: Array[Array[Int]]          = _

  private var cheapestArcs: Array[Array[Float]] = _

  private var cheapestLabels: Array[Array[String]] = _
  private var defined: Array[Array[Boolean]]       = _

  private var pathForGraph: Array[Array[Int]] = _
  private var basicCost: Float                = _

  val NONE: Int = -1

  def getPathForGraphs: Array[Array[Int]] = this.pathForGraph

  def getCheapestArcs: Array[Array[Float]] = this.cheapestArcs

  def this(vertices: Int) = {
    this()

    this.verticesCount = vertices

    if (this.verticesCount <= 0) {
      throw new Exception("Граф пустой")
    }

    this.verticleDeltas = new Array[Int](this.verticesCount)
    this.defined = Array.ofDim[Boolean](this.verticesCount, this.verticesCount)

    this.labelsOfArcs = Array.ofDim[Vector[String]](this.verticesCount, this.verticesCount)
    this.cheapestArcs = Array.ofDim[Float](this.verticesCount, this.verticesCount)
    this.duplicatedArcs = Array.ofDim[Int](this.verticesCount, this.verticesCount)

    this.matrix = Array.ofDim[Int](this.verticesCount, this.verticesCount)
    this.cheapestLabels = Array.ofDim[String](this.verticesCount, this.verticesCount)
    this.pathForGraph = Array.ofDim[Int](this.verticesCount, this.verticesCount)

    this.basicCost = 0

  }

  def addArc(label: String, u: Int, v: Int, cost: Float): Unit = {

    if (!this.defined(u)(v)) {
      this.labelsOfArcs(u)(v) = Vector[String]()
    }

    this.labelsOfArcs(u)(v) + label
    this.basicCost += cost

    if (!this.defined(u)(v) || this.cheapestArcs(u)(v) > cost) {

      this.cheapestArcs(u)(v) = cost
      this.cheapestLabels(u)(v) = label
      this.defined(u)(v) = true
      this.pathForGraph(u)(v) = v

    }

    this.matrix(u)(v) += 1
    this.verticleDeltas(u) += 1
    this.verticleDeltas(v) -= 1

  }

  def leastCostPaths(): Unit = {

    (0 until this.verticesCount)
      .foreach(
        i =>
          (0 until this.verticesCount)
            .foreach(
              j =>
                if (this.defined(j)(i)) {

                  (0 until this.verticesCount)
                    .foreach(
                      k =>
                        if (this.defined(i)(k) &&
                            (!this.defined(j)(k) ||
                            this.cheapestArcs(j)(k) > this.cheapestArcs(j)(i) + this
                              .cheapestArcs(i)(k))) {

                          this.pathForGraph(j)(k) = this.pathForGraph(j)(i)

                          this
                            .cheapestArcs(j)(k) = this.cheapestArcs(j)(i) + this.cheapestArcs(i)(k)

                          this.defined(i)(k) = true
                          if ((j == k) && this.cheapestArcs(j)(k) < 0) return

                        }
                    )

                }
            )
      )

  }

  def checkValid(): Unit = {

    for (i <- 0 until this.verticesCount) {

      for (j <- 0 until this.verticesCount) {

        if (!this.defined(i)(j)) {
          throw new Exception("Граф соединён не до конца")
        }

      }

      if (this.cheapestArcs(i)(i) < 0) {
        throw new Exception("В графе есть отрицательный цикл")
      }

    }

  }

  def phi: Float = {

    var result: Float = 0

    (0 until this.verticesCount)
      .foreach(
        i =>
          (0 until this.verticesCount)
            .foreach(j => result += this.cheapestArcs(i)(j) * this.duplicatedArcs(i)(j))
      )

    result

  }

  def cost: Float = this.basicCost + this.phi

  def findUnbalanced(): Unit = {

    var nn = 0
    var np = 0

    (0 until this.verticesCount)
      .foreach(
        i =>
          if (this.verticleDeltas(i) < 0) {
            nn += 1
          } else if (this.verticleDeltas(i) > 0) {
            np += 1
          }
      )

    this.negativeVerticles = new Array[Int](nn)
    this.postitiveVerticles = new Array[Int](np)

    nn = 0
    np = 0

    (0 until this.verticesCount)
      .foreach(
        i =>
          if (this.verticleDeltas(i) < 0) {
            nn += 1
            this.negativeVerticles(nn) = i
          } else if (this.verticleDeltas(i) > 0) {
            np += 1
            this.postitiveVerticles(np) = i
          }
      )

  }

  def findFeasible(): Unit = {

    val delta = new Array[Int](this.verticesCount)

    Array.copy(this.verticleDeltas, 0, delta, 0, this.verticesCount)

    this.negativeVerticles.indices
      .foreach(
        i =>
          this.postitiveVerticles.indices
            .foreach(j => {

              this.duplicatedArcs(i)(j) = if (-delta(i) < delta(j)) delta(i) else delta(j)
              delta(i) += this.duplicatedArcs(i)(j)
              delta(j) -= this.duplicatedArcs(i)(j)

            })
      )

  }

  def improvements: Boolean = {

    val residual = new PostmanHelper(this.verticesCount)

    this.negativeVerticles.indices
      .foreach(
        i =>
          this.postitiveVerticles.indices
            .foreach(j => {
              residual.addArc(null, i, j, this.cheapestArcs(i)(j))

              if (this.duplicatedArcs(i)(j) != 0) {
                residual.addArc(null, j, i, -this.cheapestArcs(i)(j))
              }

            })
      )

    residual.leastCostPaths()

    (0 until this.verticesCount)
      .foreach(
        i =>
          if (residual.getCheapestArcs(i)(i) < 0) {

            var k = 0
            var u = 0
            var v = 0

            var canBeSet = true

            u = i

            do {
              v = residual.getPathForGraphs(u)(i)

              if (residual
                    .getCheapestArcs(u)(v) < 0 && (canBeSet || k > this.duplicatedArcs(v)(u))) {

                k = this.duplicatedArcs(v)(u)
                canBeSet = false

              }

              u = v

            } while (u != i)

            u = i

            do {

              v = residual.getPathForGraphs(u)(i)

              if (residual.getCheapestArcs(u)(v) < 0) {
                this.duplicatedArcs(v)(u) -= k
              } else {
                this.duplicatedArcs(u)(v) += k
              }

              u = v

            } while (u != i)

            true

          }
      )

    false

  }

  def findPath(from: Int, graph: Array[Array[Int]]): Int = {

    (0 until this.verticesCount)
      .foreach(
        i =>
          if (graph(from)(i) > 0) {
            i
          }
      )

    NONE

  }

  def printCPT(startVertex: Int): Unit = {

    var v = startVertex

    val arcs  = Array.ofDim[Int](this.verticesCount, this.verticesCount)
    val graph = Array.ofDim[Int](this.verticesCount, this.verticesCount)

    (0 until this.verticesCount)
      .foreach(
        i =>
          (0 until this.verticesCount)
            .foreach(j => {
              arcs(i)(j) = this.matrix(i)(j)
              graph(i)(j) = this.duplicatedArcs(i)(j)
            })
      )

    while (true) {

      var u = v
      v = this.findPath(u, graph)

      if (v != NONE) {

        graph(u)(v) -= 1
        var p = this.pathForGraph(u)(v)

        u = p

        while (u != v) {

          println(s"Берём дугу ${this.cheapestLabels(u)(p)} из $u в $p")
          p = this.pathForGraph(u)(v)
          u = p

        }

      } else {

        val bridgeVertex = this.pathForGraph(u)(startVertex)

        if (arcs(u)(bridgeVertex) == 0) {
          break
        }

        v = bridgeVertex

        for (i <- 0 until bridgeVertex) {

          if (i != bridgeVertex && arcs(u)(i) > 0) {
            v = i
            break
          }

        }

        arcs(u)(v) -= 1

        println(s"Берём дугу ${labelsOfArcs(u)(v)(arcs(u)(v))} из $u в $v")

      }

    }

  }

  def solve(): Unit = {
    this.leastCostPaths()
    this.checkValid()
    this.findUnbalanced()
    this.findFeasible()
    while (this.improvements) {}
  }

}
