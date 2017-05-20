package com.toptal.demo

import org.apache.spark.SparkContext
import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}

class DevelopersByCountriesTest extends FunSpec with Matchers with BeforeAndAfter {
  var sc: SparkContext = TestSparkContext

  describe("DevelopersByCountries") {
    it("calculate statistics for countries") {
      val country1 = Country(1, "Russia")
      val country2 = Country(2, "Belarus")

      val developers = List(
        Developer(10, "Ivan", country1.id),
        Developer(11, "Dmitry", country1.id),
        Developer(12, "Petr", country1.id),
        Developer(13, "Yan", country2.id),
        Developer(14, "Layvon", country2.id)
      )

      DevelopersByCountries(
        sc.parallelize(developers),
        sc.parallelize(country1 :: country2 :: Nil)
      ) shouldEqual List(
        CountryStat("Belarus", 2),
        CountryStat("Russia", 3)
      )
    }
  }
}
