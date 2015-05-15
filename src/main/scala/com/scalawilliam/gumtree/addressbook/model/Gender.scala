package com.scalawilliam.gumtree.addressbook.model

object Gender {
  def unapply(name: String): Option[Gender] =
    PartialFunction.condOpt(name) {
      case "Male" => Male
      case "Female" => Female
    }
}

sealed trait Gender
case object Female extends Gender

case object Male extends Gender
