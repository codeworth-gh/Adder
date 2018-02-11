#!/bin/bash
echo "Adder - Simple app for summing grades."
echo "Type numbers to add them. Empty line (double enter) to display sum."
exec scala "$0" "$@"
!#
/*
Usage: ./adder.scala
*/
import java.nio.file.{Paths,Path,Files}
import java.util.Date

object App {
  private val fmt = new java.text.SimpleDateFormat("hh:mm:ss.S")
  import Console.{GREEN, RED, RESET, YELLOW, UNDERLINED}

  val numScanner = "^[0-9]+(\\.[0-9]*)?$".r
  var currentSum=0.0

  def printSum() = Console.println(s"${RESET}${YELLOW}${currentSum}${RESET}")

  def start() = {
    var go = true
    while ( go ) {
      Console.print(s"${RESET}${GREEN}> ${RESET}")
      val line = io.StdIn.readLine()
      if ( line == null ) {
        go = false

      } else if ( line.isEmpty ) {
        if ( currentSum != 0) {
          printSum()
          currentSum = 0.0
        }

      } else {
        numScanner.findFirstIn(line) match {
          case Some(numberString) => currentSum = currentSum+numberString.toDouble
          case None => line match {
            case "\\q" => go = false
            case _ => println( "decimal numbers, or \\q")
          }
        }
      }
    }

    if ( currentSum != 0) {
      printSum()
    }
  }
}

App.start()