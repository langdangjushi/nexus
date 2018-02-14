package com.chinapex.nexus.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * created by pengmingguo on 2/14/18
 */
public interface StorageService {
    void store(MultipartFile file);
}
