package model

import scala.io.Source
import scala.util.Random

object ProcessingClass {

  val itemFileName = "src/main/resources/item.txt"
  val qualityItemName = "src/main/resources/quality.txt"
  val prefix = "src/main/resources/prefix.txt"
  val postfix = "src/main/resources/postfix.txt"

  def generate(isItem: Boolean): String = {
    if (isItem) {
      getRandom(itemFileName)
    } else {
      getRandom(qualityItemName)
    }

  }

  def generate(): String = {

    if (getRandomNumber() % 2 == 0) {
      getRandom(qualityItemName)
    }else{
      getRandom(itemFileName)
    }
  }
  
  def generatePrefix():String = {
    getRandom(prefix)
  }

  def generatePostfix(): String = {
    getRandom(postfix)
  }


  def getRandom(filename: String): String = {
    val strings = Source.fromFile(filename).getLines().toList
    Random.shuffle(strings).headOption.getOrElse("No strings in the file.")
  }

  def getRandomNumber(): Integer = {
    val random = new Random()

    val min = 1
    val max = 100

    min + random.nextInt(max - min)
  }

}

