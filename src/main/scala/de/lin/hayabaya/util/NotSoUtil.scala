package de.lin.hayabaya.util

import com.typesafe.scalalogging.LazyLogging

/**
 * Created by cain on 24/06/16.
 */
object NotSoUtil extends LazyLogging {

  /**
   * Always be case insensitive when evaluating strings for "true"
   *
   * @param s string being true,True,TRUE,TruE etc.
   * @return true if string matches "true", "True", "TRUE" and so forth
   */
  private def isTrue(s: String): Boolean = {
    "true".equalsIgnoreCase(s)
  }

}
