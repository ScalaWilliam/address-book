package com.scalawilliam.gumtree.addressbook.model

object Int {
  def unapply(v: String) =
    try Option(v.toInt)
    catch { case _: NumberFormatException => None }
}
