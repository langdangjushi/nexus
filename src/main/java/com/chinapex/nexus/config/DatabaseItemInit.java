package com.chinapex.nexus.config;

import com.chinapex.nexus.dao.WebModuleRepositoy;
import com.chinapex.nexus.model.WebModule;
import java.util.LinkedList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/** created by pengmingguo on 4/2/18 */
@Configuration
public class DatabaseItemInit {

  @Autowired private WebModuleRepositoy webRepo;

  @Autowired private DataSource dataSource;

  private static String[] WEBMODULE = {
    "DASHBOARD", "DATA", "ANALYTICS", "TRAITS", "SEGMENTS", "APPS", "SYSTEM", "IQ"
  };

  @EventListener(ApplicationReadyEvent.class)
  // TODO 这个判别比较粗糙
  public void doSomeDataBaseItemInit() {
    if (webRepo.count() < WEBMODULE.length) {
      webRepo.deleteAll();
      LinkedList<WebModule> webModules = new LinkedList<>();
      for (int i = 0; i < WEBMODULE.length; i++) {
        WebModule module = new WebModule();
        module.setName(WEBMODULE[i]);
        module.setId(i + 1);
        webModules.add(module);
      }
      webRepo.save(webModules);
    }
  }
}
