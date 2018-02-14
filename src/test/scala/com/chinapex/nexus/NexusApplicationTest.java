package com.chinapex.nexus;

import com.chinapex.nexus.dao.OrganizationRepository;
import com.chinapex.nexus.model.EventGroup;
import com.chinapex.nexus.model.Organization;
import java.util.LinkedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/** created by pengmingguo on 2/14/18 */
@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class NexusApplicationTest {
  @Autowired
  private OrganizationRepository orgRepo;

  @Test
  public void testNestCollectionIfIsNull() {
    Organization org = new Organization();
    org.setName("aaaa");
    EventGroup group = new EventGroup();
    group.setName("abc");
    org.addEventGroup(group);
    group.setOrganization(org);
    orgRepo.save(org);

    Organization aaaa = orgRepo.findByName("aaaa");
    System.out.println(aaaa.getEventGroups());
  }
}
