package com.chinapex.test

import com.chinapex.nexus.service.ModelListener
import org.junit.Test

/**
  * created by pengmingguo on 2/8/18
  */
class ModelModifyListenerTest extends ModelListener[Int]{

  @Test
  def a : Unit = {
    this.create(1)
  }
}
