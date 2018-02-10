package com.chinapex.nexus.service.impl

import com.chinapex.nexus.model.Organization
import com.chinapex.nexus.service.ModelListener
import org.springframework.stereotype.Component

/**
  * created by pengmingguo on 2/10/18
  */
@Component
class DataBaseListener extends ModelListener[Organization]{
  override def create(o: Organization): Unit = {
    // create hbase table, es index etc
  }
}
