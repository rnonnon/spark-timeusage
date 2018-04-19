package timeusage

import scala.util.matching.Regex

sealed trait Activity {
  val Regex:Regex
  def unapply(arg: String): Option[String] =  Regex.findFirstIn(arg).map(_ => arg)
}

object Primary extends Activity {
  val Regex = "^t(01|03|11|1801|1803)".r
}
object Working extends Activity {
  val Regex = "^t(05|1805)".r
}
object Leisure extends Activity {
  val Regex = "^t(02|04|06|07|08|09|10|12|13|14|15|16|18)".r
}
