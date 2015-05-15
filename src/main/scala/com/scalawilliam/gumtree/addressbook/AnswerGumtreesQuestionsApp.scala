package com.scalawilliam.gumtree.addressbook

import scala.language.reflectiveCalls
import model._
import java.time.LocalDate

object AnswerGumtreesQuestionsApp extends App {

  // Good enough to use String for us frankly
  implicit val dateOrdering = Ordering.by[LocalDate, String](_.toString)

  val people = scala.io.Source.fromFile("AddressBook").getLines()
    .collect { case Person(person) => person }.toList

  def ask(question: String) = new {
    def answer[T](result: => T) = new {
      println(s"Question: $question")
      println(s"Answer: $result")
    }
  }

  ask {
    "How many males are in the address book?"
  } answer {
    people.count(_.gender == Male)
  }

  ask {
    "Who is the oldest person in the address book"
  } answer {
    people.minBy(_.dob.date)
  }

  ask {
    "How many days older is Bill than Paul?"
  } answer {
    for {
      bill <- people.find(_.name startsWith "Bill")
      paul <- people.find(_.name startsWith "Paul")
    } yield java.time.Period.between(bill.dob.date, paul.dob.date).getDays
  }

}
