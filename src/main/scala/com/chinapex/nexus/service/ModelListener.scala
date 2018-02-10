package com.chinapex.nexus.service

/**
  * created by pengmingguo on 2/8/18
  */
trait ModelListener[A] {

  def create(o : A) : Unit = Unit

  def delete(o : A) : Unit = Unit

  def update(o : A) : Unit = Unit
}
