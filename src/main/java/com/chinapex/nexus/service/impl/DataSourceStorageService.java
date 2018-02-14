package com.chinapex.nexus.service.impl;

import com.chinapex.nexus.dto.LoginToken;
import com.chinapex.nexus.exception.StorageException;
import com.chinapex.nexus.service.StorageService;
import com.chinapex.nexus.util.TokenUtil;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/** created by pengmingguo on 2/14/18 */
@Service
public class DataSourceStorageService implements StorageService {
  private static Logger  logger = LoggerFactory.getLogger(DataSourceStorageService.class);

  @Override
  public void store(MultipartFile file) {

    // first save to local disk
    LoginToken token = TokenUtil.getToken();
    String filename =
        System.getProperty("user.dir")
            + "/file_upload/"
            + token.getOrgName()
            + "/"
            + StringUtils.cleanPath(file.getOriginalFilename());
    try {
      // make dirs in case not exist
      Files.createDirectories(Paths.get(filename).getParent());
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file " + filename);
      }
      if (filename.contains("..")) {
        throw new StorageException(
            "Cannot store file with relative path outside current directory " + filename);
      }
      Files.copy(file.getInputStream(), Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new StorageException("Failed to store file " + filename, e);
    }

    // then save to hbase or hive or es

  }
}
