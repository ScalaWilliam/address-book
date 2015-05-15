package com.scalawilliam.gumtree.addressbook.model

case class Person(name: String, gender: Gender, dob: Dob)
object Person {
  val regex = """([^,]+), (Male|Female), (.*)""".r
  def unapply(line: String): Option[Person] =
    PartialFunction.condOpt(line) {
      case regex(name, Gender(gender), Dob(dob)) =>
        Person(name = name, gender = gender, dob = dob)
    }
}