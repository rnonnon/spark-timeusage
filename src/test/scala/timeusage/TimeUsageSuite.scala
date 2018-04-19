package timeusage

import org.apache.spark.sql.functions._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers}
import timeusage.TimeUsage._

@RunWith(classOf[JUnitRunner])
class TimeUsageSuite extends FunSuite with BeforeAndAfterAll with Matchers {

  override def afterAll(): Unit = {
    spark.stop()
  }

  test("Columns should be constructed") {
    val columnNames = List("tucaseid","t0123","t0323","t0567","t0910","t1805","t0234","t1123","gemetsta")
    val expectedPrimary = List(col("t0123"),col("t0323"), col("t1123"))
    val expectedWorking = List(col("t0567"), col("t1805"))
    val expectedLeisure = List(col("t0910"),col("t0234"))
    val (primary, working, leisure) = classifiedColumns(columnNames)
    primary should contain theSameElementsAs expectedPrimary
    working should contain theSameElementsAs expectedWorking
    leisure should contain theSameElementsAs expectedLeisure
  }
  test("Matcher should match on starting") {
    val columnNames = List("t00123")
    val (primary, working, leisure) = classifiedColumns(columnNames)
    primary shouldBe empty
    working shouldBe empty
    leisure shouldBe empty
  }
}
