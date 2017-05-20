package com.toptal.demo

import org.apache.spark.rdd.RDD

object DevelopersByCountries {
  def apply(developers: RDD[Developer], countries: RDD[Country]): List[CountryStat] = {
    countries
      .map(c => c.id -> c)
      .join(developers.map(d => d.countryId -> d))
      .map { case (countryId, (country, developer)) =>
        country.name
      }.countByValue()
      .map { case (countryName, count) =>
        CountryStat(countryName, count)
      }.toList.sortBy(_.name)
  }
}
