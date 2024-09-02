package com.kpler.scala.kata.introduction

import java.io.{BufferedWriter, FileWriter}
import scala.annotation.tailrec
import scala.io.Source
import scala.util.Using

object Bomberman {


  case class Point(x: Int, y: Int)

  case class Board(width: Int, height: Int, content: List[String]) {

    /*
     Return p plus the adjacent list of points to p at the coordinates:
     - (p.x - 1, y)
     - (p.x + 1, y)
     - (p.x, p.y - 1)
     - (p.x, p.y + 1)
    The coordinates must be valid:
     - 0 <= x < width
     - 0 <= y < height
    p itself must be part of the returned list

    hints: Use a for comprehension

    time: 8 minutes
    */
    private def neighbors(p: Point): List[Point] = ???

    /*
     Return the points inside the board where a bomb is planted
     A bomb is defined by the 'O' character

    hints: You can get a value and its index from an iterable with 'zipWithIndex'
    example:
      List('a', 'b', 'c').zipWithIndex => List( ('a', 0), ('b', 1), ('c', 2) )
    A String is an iterator
      "I love Scala".zipWithIndex => Vector(
      (I,0), ( ,1), (l,2), (o,3), (v,4), (e,5), ( ,6), (S,7), (c,8), (a,9), (l,10), (a,11)
      )

    time: 5 minutes
    */
    def getBombs: List[Point] = ???

    /*
      Returns the new board after the bombs located at 'bombLocations' have detonated

      Each cell inside the board at the coordinates of a bomb and all its neighbors become an empty cell.
      An empty cell is defined by the '.' character

      hints:
      Re-use the nested internal function seen earlier

      You might want to temporarily convert a row (String) of the board into a StringBuffer
      so that you can replace one character with insert
        StringBuffer("I love Scala").insert(0, 'i') => "i love Scala"

      You can access an element of a list with '()'
        val lst = List(4, 5, 6)
        lst(1) => 5

      You cannot modify a list directly (immutability) but build a new updated list with 'updated'
        List(4, 5, 6).updated(0, 10) => List(10, 5, 6)

     time: 12 minutes
     */
    def detonate(bombLocations: List[Point]): Board = {
      val destroyed = bombLocations.flatMap(this.neighbors)

      @tailrec
      def internal(b: List[Point], newContent: List[StringBuffer]): List[String] = b match {
        case Nil => newContent.map(_.toString)
        case destroyed :: rest =>
          val line = newContent(destroyed.y).insert(destroyed.x, '.')
          internal(rest, newContent.updated(destroyed.y, line))
      }

      val updatedContent = internal(destroyed, content.map(StringBuffer(_)))
      Board(this.width, this.height, updatedContent)
    }

    /*
     Returns a new board with bombs inside every cell
     A bomb is defined by the 'O' character

     hints:
     You have to replace each string in content with a new string filled with 'O'
     You might use the 'map' function on both 'List' and 'String'

     time: 6 minutes
     */
    def fullOfBombs(): Board = ???
  }


  def bomberman(nbSeconds: Int, board: Board): Board = nbSeconds match {
    case 0 | 1 => board
    case _ if nbSeconds % 2 == 0 => board.fullOfBombs()
    case _ if nbSeconds % 4 == 3 => board.fullOfBombs().detonate(board.getBombs)
    case _ => board

  }

  @main def bomberman_main(args: String*): Unit = {
    Using(Source.fromFile("bomberman-input.txt")) { source =>
      val allLines = source.getLines.toList
      val firstLine = allLines.head.strip().split(" ")
      val r = firstLine(0).toInt
      val c = firstLine(1).toInt
      val n = firstLine(2).toInt

      val result = bomberman(n, Board(c, r, allLines.tail))

      Using(new BufferedWriter(new FileWriter("bomberman-output-scala.txt"))) { writer =>
        writer.write(result.content.mkString("\n"))
      }


    }
  }
}
