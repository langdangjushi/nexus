package com.chinapex.nexus.service

import com.chinapex.nexus.model.Organization

/**
  * created by pengmingguo on 2/8/18
  */
trait OrganizationService {

  def create(o : Organization) : Organization

  def update(o : Organization) : Organization

  def register(o : ModelListener[Organization]) : Unit

}
