package com.chinapex.nexus.controller;

import com.chinapex.nexus.dao.DataResourceTypeRepository;
import com.chinapex.nexus.dao.OrganizationRepository;
import com.chinapex.nexus.dto.DataResourceDto;
import com.chinapex.nexus.dto.DataResourceTypeDto;
import com.chinapex.nexus.dto.DataResourceTypeResponse;
import com.chinapex.nexus.dto.LoginToken;
import com.chinapex.nexus.model.DataResource;
import com.chinapex.nexus.model.DataResourceType;
import com.chinapex.nexus.model.Organization;
import com.chinapex.nexus.util.Msg;
import com.chinapex.nexus.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/** created by pengmingguo on 2/13/18 */
@RestController
@RequestMapping("api/v1/data_resources")
public class DataResourceController {

  @Autowired private OrganizationRepository orgRepo;

  @Autowired private DataResourceTypeRepository dtRepo;

  @RequestMapping("/")
  public Msg listDataResource() {
    LoginToken token = TokenUtil.getToken();
    Organization org = orgRepo.findByName(token.getOrgName());
    Collection<DataResource> dataResources = org.getDataResources();
    List<DataResourceDto> dtos =
        dataResources
            .stream()
            .map(
                dr -> {
                  DataResourceDto dto = new DataResourceDto();
                  dto.setName(dr.getName());
                  dto.setDataType(dr.getDataResourceType().getName());
                  dto.setLastSyncTime(dr.getUpdatedTime());
//                  dto.setSyncStatus();
                  return dto;
                })
            .collect(Collectors.toList());
    HashMap data = new HashMap(1);
    data.put("resources", dtos);
    return Msg.ok().data(data);
  }

  @GetMapping("types")
  public Msg listType() {
    LoginToken token = TokenUtil.getToken();
    Organization org = orgRepo.findByName(token.getOrgName());
    Set<DataResourceType> orgDataResourceTypes =
        org.getDataResourceTypes().stream().collect(Collectors.toSet());
    Iterable<DataResourceType> all = dtRepo.findAll();
    Map<String, List<DataResourceType>> groups =
        ((Collection<DataResourceType>) all)
            .stream()
            .collect(Collectors.groupingBy(DataResourceType::getGroupName));
    List<DataResourceTypeResponse> response =
        groups
            .entrySet()
            .stream()
            .map(
                e -> {
                  DataResourceTypeResponse resp = new DataResourceTypeResponse();
                  resp.setGroupName(e.getKey());
                  List<DataResourceTypeDto> types =
                      e.getValue()
                          .stream()
                          .map(
                              v -> {
                                DataResourceTypeDto dto = new DataResourceTypeDto();
                                dto.setText(v.getName());
                                dto.setIsActive(orgDataResourceTypes.contains(v));
                                return dto;
                              })
                          .collect(Collectors.toList());
                  resp.setMarkets(types);
                  return resp;
                })
            .collect(Collectors.toList());
    HashMap data = new HashMap();
    data.put("dataresourceTypes", response);
    return Msg.ok().data(data);
  }
}
