package com.scalawilliam.gumtree.addressbook.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

case class Dob(date: LocalDate)

object Dob {
  /**
   * The 'YY' bit is ill-defined, we we'll assume that anyone
   * under 15 is actually born recently
   */
  val fmt = DateTimeFormatter.ofPattern("dd/MM/YY")
  val rgx = """(\d\d)/(\d\d)/(\d\d)""".r
  def unapply(str: String): Option[Dob] =
    PartialFunction.condOpt(str) {
      case rgx(Int(d), Int(m), Int(y)) if y <= 15 =>
        Dob(LocalDate.of(2000 + y, m, d))
      case rgx(Int(d), Int(m), Int(y)) =>
        Dob(LocalDate.of(1900 + y, m, d))
    }
}
