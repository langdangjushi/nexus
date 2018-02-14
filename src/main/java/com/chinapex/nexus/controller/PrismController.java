package com.chinapex.nexus.controller;

import com.chinapex.nexus.dao.OrganizationRepository;
import com.chinapex.nexus.dto.*;
import com.chinapex.nexus.model.Organization;
import com.chinapex.nexus.service.PrismHttpService;
import com.chinapex.nexus.util.Msg;
import com.chinapex.nexus.util.TokenUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created by pengmingguo on 2/13/18
 */
@RestController
@RequestMapping("/api/v1")
public class PrismController {
    private static Logger logger = LoggerFactory.getLogger(PrismController.class);

    @Autowired
    private PrismHttpService prismSrv;

    @Autowired
    private OrganizationRepository orgRepo;

    @GetMapping("eventmanage/getprojects")
    public Msg listPrismProject() {
        LoginToken token = TokenUtil.getToken();
        Organization org = orgRepo.findByName(token.getOrgName());
        List<PrismProject> projects = null;
        String prismToken = TokenUtil.prismToken(org.getPrismToken());
        try {
            projects = prismSrv
                    .projects(prismToken)
                    .execute().body();
        } catch (Exception e) {
            logger.error("call prism error org={}", token.getOrgName(), e);
        }

        if (projects == null) return Msg.err().data("call prism error!");

        List<PrismProjectResponse> prismProjectResponses = projects
                .stream()
                .map(p -> {
                            PrismProjectResponse proj = new PrismProjectResponse();
                            proj.setProjectId(p.getPk());
                            proj.setProjectName(p.getName());
                            // container
                            if (CollectionUtils.isNotEmpty(p.getContainers())) {
                                List<PrismContainer> containers = p.getContainers().stream().map(c -> {
                                    PrismContainer container = new PrismContainer();
                                    container.setContainerId(c.getPk());
                                    container.setContainerName(c.getName());

                                    // triggers
                                    List<PrismTrigger> triggers = null;
                                    try {
                                        List<PrismBaseDto> body = prismSrv.triggers(prismToken).execute().body();
                                        triggers = body.stream().map(t -> {
                                            PrismTrigger trigger = new PrismTrigger();
                                            trigger.setTriggerId(t.getRule_number());
                                            trigger.setTriggerName(t.getName());
                                            return trigger;
                                        }).collect(Collectors.toList());
                                    } catch (Exception e) {
                                        logger.error("call prism trigger error org={} project={} container={}", token.getOrgName(), p.getPk(), c.getPk(), e);
                                    }
                                    container.setTriggers(triggers);
                                    return container;
                                }).collect(Collectors.toList());

                                proj.setContainers(containers);
                            }
                            // pixels
                            if (CollectionUtils.isNotEmpty(p.getPixels())) {
                                List<String> pixels = p.getPixels().stream().map(PrismBaseDto::getName).collect(Collectors.toList());
                                proj.setPixels(pixels);
                            }
                            return proj;
                        }
                ).collect(Collectors.toList());
        return Msg.ok().data(prismProjectResponses);
    }

}
