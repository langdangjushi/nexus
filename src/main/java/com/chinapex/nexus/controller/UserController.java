package com.chinapex.nexus.controller;

import com.chinapex.nexus.dao.UserRepository;
import com.chinapex.nexus.dto.*;
import com.chinapex.nexus.model.User;
import com.chinapex.nexus.util.Msg;
import com.chinapex.nexus.util.TokenUtil;
import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.chinapex.nexus.util.HttpResponseCode.USER_ALREADY_EXIST;
import static com.chinapex.nexus.util.TokenUtil.getToken;

/**
 * created by pengmingguo on 2/9/18
 */
@RestController
@RequestMapping("/api/v1/acl/")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepo;

    @GetMapping("member-management")
    public Msg listMember() {
        LoginToken token = getToken();
        User parent = userRepo.findByName(token.getUserName());

        if (parent == null) return Msg.err().data("user does not exist");

        Collection<User> children = parent.getChildren();

        // add parent self
        children.add(parent);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<OrgMember> members = children.stream().map(u -> {
            OrgMember m = new OrgMember();
            m.setUserId(u.getId());
            m.setUserName(u.getName());
            m.setEmail(u.getEmail());
            m.setCreatedTime(sdf.format(u.getCreatedTime()));
            m.setUpdatedTime(sdf.format(u.getUpdatedTime()));
            m.setRole(u.getRole());
            m.setStatus(u.getStatus());
            return m;
        }).collect(Collectors.toList());

        return Msg.err().data(new PageImpl<>(members));
    }

    /**
     * 管理员邀请
     *
     * @param request
     * @return
     */
    @PostMapping("invite")
    public Msg inviteUser(@RequestBody InviteUserRequest request) {
        logger.info("received invite request {}", request);

        LoginToken token = getToken();
        User parent = userRepo.findByName(token.getUserName());
        // 检测是否是管理员，只有管理员有资格邀请
        boolean userIsNotAdmin = !parent.getRole().equals(User.ADMIN);
        if (userIsNotAdmin)
            return Msg.ok().data("Sorry! You are not admin!");

        User old = userRepo.findByEmail(request.getEmail());
        if (old == null) {
            old = new User();
            old.setEmail(request.getEmail());
            old.setOrganization(parent.getOrganization());
            old.setName(UUID.randomUUID().toString());
            old.setStatus(User.INVITED);
            old.setParent(parent);
        } else {
            old.setUpdatedTime(new Date());
        }
        userRepo.save(old);
        return null;
    }

    /**
     * 管理员删除用户
     *
     * @param userId
     * @return
     */
    @GetMapping("user/delete/{userId}")
    public Msg deleteUser(@PathVariable Integer userId) {
        userRepo.delete(userId);
        return Msg.ok();
    }

    /**
     * 用户自己激活
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("user/invite")
    public Msg activateUser(@RequestBody @NotNull ActivateUserRequest request, HttpServletResponse response) {
        logger.info("user self activation user={}, email={}", request.getName(), request.getEmail());
        // 该用户名已经被占用
        boolean userNameExist = userRepo.existsByName(request.getName());
        if (userNameExist)
            return new Msg(USER_ALREADY_EXIST, null, "user name already exists");

        User user = userRepo.findByEmail(request.getEmail());
        String cryptPassword = Md5Crypt.md5Crypt(request.getPassword().getBytes());
        user.setName(request.getName());
        user.setPassword(cryptPassword);
        user.setStatus(User.ACTIVATED);

        String tokenStr = TokenUtil.createTokenStr(user);
        response.setHeader(TokenUtil.TOKEN_NAME, tokenStr);

        userRepo.save(user);
        return Msg.ok();
    }

    @GetMapping("user/invite/{uuid}")
    public Msg acceptInvitation(@PathVariable String uuid) {
        User user = userRepo.findByName(uuid);
        if (user == null) return Msg.err().data("this url is invalid");

        boolean activated = user.getStatus() == User.ACTIVATED;
        if (activated)
            return new Msg(401, null, "Already activated!");

        boolean expired = (System.currentTimeMillis() - user.getUpdatedTime().getTime()) > 24 * 60 * 60 * 1000L;
        if (expired)
            return Msg.err().data("this url is expired!");
        HashMap data = new HashMap(1);
        data.put("email", user.getEmail());
        return Msg.ok().data(data);
    }

    @PostMapping("user/config")
    public Msg editUser(@RequestBody ResetUserRequest request) {
        LoginToken token = getToken();
        User user = userRepo.findOne(request.getUserId());

        boolean isParent = user.getParent().getName().equals(token.getOrgName());
        if (!isParent) return Msg.err().data("you are not parent of this user!");

        user.setWebModules(request.getPrivilege());
        user.setEmail(request.getEmail());
        userRepo.save(user);
        return Msg.ok();
    }

    @GetMapping("user/config/{userId}")
    public Msg getUserPrivilege(@PathVariable Integer userId) {
        return null;
    }


}
