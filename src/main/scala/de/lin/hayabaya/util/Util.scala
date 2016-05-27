package de.lin_magdeburg.hayabaya.util

import com.typesafe.scalalogging.LazyLogging

/**
  * Created by admin on 15/01/2016.
  */
object Util extends LazyLogging {

  val log = logger

  @throws[IllegalArgumentException] def throwErrorForTesting = throw new IllegalArgumentException("Threw IllegalArgumentException for testing")

}
