package de.lin_magdeburg.hayabaya

import com.typesafe.scalalogging.LazyLogging

/**
  * Created by admin on 15/01/2016.
  */
object Util extends LazyLogging{

  val sessionLogger = logger

  @throws[IllegalArgumentException]
  def throwError = throw new IllegalArgumentException("The param was not found")

}
