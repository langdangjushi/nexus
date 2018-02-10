package com.chinapex.nexus.service.impl

import com.chinapex.nexus.dao.OrganizationRepository
import com.chinapex.nexus.model.Organization
import com.chinapex.nexus.service.{ModelListener, ModelListenerProcessor, OrganizationService}
import com.chinapex.nexus.util.ModelOperation._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


/**
  * created by pengmingguo on 2/8/18
  */
@Service
class OrganizationServiceImpl
    extends OrganizationService
    with ModelListenerProcessor[Organization] {

  @Autowired
  private var orgRepo : OrganizationRepository = _

  @Transactional
  override def create(o: Organization): Organization = {
    // insert to db
    val saved = orgRepo.save(o)
    // process listener
    super.process(saved,CREATE)
    saved
  }

  @Transactional
  override def update(o: Organization): Organization = {
    // update to db
    orgRepo.save(o)
    super.process(o,UPDATE)
    o
  }

  override def register(o: ModelListener[Organization]): Unit = {
    super.register(o)
  }
}
