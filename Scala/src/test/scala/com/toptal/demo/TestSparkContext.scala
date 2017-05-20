package com.toptal.demo

import org.apache.spark.{SparkConf, SparkContext}

object TestSparkContext extends SparkContext(
  new SparkConf()
  .setMaster("local[2]")
  .setAppName("example-spark")
)
