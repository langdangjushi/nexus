package com.chinapex.nexus.service

import java.util.concurrent.CopyOnWriteArrayList

import com.chinapex.nexus.util.ModelOperation._

/**
  * created by pengmingguo on 2/8/18
  */
trait ModelListenerProcessor[A] {

  // thread safe
  private val listeners =
    new CopyOnWriteArrayList[ModelListener[A]]()

  protected def process(o: A, op: ModelOperation): Unit = {
    op match {
      case CREATE => doProcess(l => l.create(o))
      case UPDATE => doProcess(l => l.update(o))
      case DELETE => doProcess(l => l.delete(o))
    }
  }

  private def doProcess(f: ModelListener[A] => Unit): Unit = {
    val it = listeners.listIterator()
    while (it.hasNext) {
      val cur = it.next()
      f(cur)
    }
  }

  protected def register(o : ModelListener[A]) : Unit = {
    listeners.add(o)
  }
}
