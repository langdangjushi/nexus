package com.chinapex.nexus;

import com.chinapex.nexus.dao.ConfigRepository;
import com.chinapex.nexus.dao.OrganizationRepository;
import com.chinapex.nexus.model.Config;
import com.chinapex.nexus.model.EventGroup;
import com.chinapex.nexus.model.Label;
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
  @Autowired
  private ConfigRepository configRepo;

  @Test
  public void testNestCollectionIfIsNull() {
    Organization org = new Organization();
    org.setName("aaaa");
    EventGroup group = new EventGroup();
    group.setName("abc");
    org.addEventGroup(group);
    group.setOrganization(org);
    Label label = new Label();
    label.setOrganization(org);
    label.setName("label-1");
    org.addLabel(label);
    orgRepo.save(org);

    Organization aaaa = orgRepo.findByName("aaaa");
    System.out.println(aaaa.getEventGroups());

    Config parrent = new Config();
    parrent.setName("a");
    parrent.setValue("b");

    LinkedList<Config> children = new LinkedList<>();
    Config child1 = new Config();
    child1.setName("b");
    child1.setValue("d");
    child1.setParent(parrent);
    Config child2 = new Config();
    child2.setName("e");
    child2.setValue("f");
    child2.setParent(parrent);
    children.add(child1);
    children.add(child2);
    parrent.setChildren(children);
    configRepo.save(parrent);

    Config parent = configRepo.findOne(1);
    System.out.println(parent);
  }
}
