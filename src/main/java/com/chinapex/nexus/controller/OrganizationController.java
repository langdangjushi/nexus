package com.chinapex.nexus.controller;

import com.chinapex.nexus.dao.OrganizationRepository;
import com.chinapex.nexus.dao.UserRepository;
import com.chinapex.nexus.dto.CreateOrganizationRequest;
import com.chinapex.nexus.dto.CreateOrganizationResponse;
import com.chinapex.nexus.dto.LoginRequest;
import com.chinapex.nexus.model.Organization;
import com.chinapex.nexus.model.User;
import com.chinapex.nexus.service.OrganizationService;
import com.chinapex.nexus.util.Msg;
import com.chinapex.nexus.util.TokenUtil;
import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by pengmingguo on 2/10/18
 */
@RestController
@RequestMapping("/api/v1/users")
public class OrganizationController {
    private static Logger logger = LoggerFactory.getLogger(Organization.class);

    @Autowired
    private OrganizationService orgSrv;

    @Autowired
    private OrganizationRepository orgRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("createUser")
    @Transactional
    public CreateOrganizationResponse createOrganization(@RequestBody CreateOrganizationRequest request) {
        logger.info("create organization and related user {}", request);
        // organization to lower case
        String normalized = request.getOrgName().toLowerCase();
        if (orgRepo.existsByName(normalized))
            return new CreateOrganizationResponse(false, "This organization already exist!");
        Organization organization = new Organization();

        organization.setName(normalized);
        organization.setPrismToken(request.getToken());
        // 获取到 数据库 id
        organization = orgSrv.create(organization);

        User user = new User();
        user.setName(request.getOrgName());
        user.setOrganization(organization);
        user.setEmail(request.getEmail());
        user.setRole(User.ADMIN);
        user.setStatus(User.ACTIVATED);
        user.setPassword(request.getPassword());

        userRepo.save(user);

        return new CreateOrganizationResponse(true, "create organization success");
    }

    private Pattern encyptPasswordPattern = Pattern.compile("^(\\$1\\$.*?)\\$.*");

    @PostMapping("validation")
    public Msg login(@RequestBody LoginRequest request, HttpServletResponse response) {
        // 用户既可以用用户名又可以用邮箱登录
        User user = userRepo.findByName(request.getUsername());

        if (user == null)
            user = userRepo.findByEmail(request.getUsername());
        // 都没有
        if (user == null)
            return Msg.err().data("Sorry! Are you hacker?");
        if (user.getOrganization() == null)
            return Msg.err().data("Where are you from?");
        if (user.getStatus() != User.ACTIVATED)
            return Msg.err().data("You are in invalid status");
        Matcher matcher = encyptPasswordPattern.matcher(user.getPassword());
        matcher.matches();
        String salt = matcher.group(1);
        String password = Md5Crypt.md5Crypt(request.getPassword().getBytes(), salt);
        if (!user.getPassword().equals(password))
            return Msg.err().data("password error");
        response.setHeader(TokenUtil.TOKEN_NAME, TokenUtil.createTokenStr(user));
        return Msg.ok();
    }
}
