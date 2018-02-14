package com.chinapex.nexus.controller;

import com.chinapex.nexus.dao.OrganizationRepository;
import com.chinapex.nexus.service.StorageService;
import com.chinapex.nexus.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * created by pengmingguo on 2/13/18
 */
@RestController
@RequestMapping("/api/v1/file_upload")
public class FileUploadController {
    @Autowired
    private OrganizationRepository orgRepo;

    @Autowired
    private StorageService storageService;

    @PostMapping("excel")
    public Msg uploadExcel(@RequestParam MultipartFile xlsx) {

        return null;
    }
}
