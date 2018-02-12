package com.chinapex.nexus.dao;

import com.chinapex.nexus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * created by pengmingguo on 2/8/18
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByName(String name);

    @Query(value = "select u from t_user u where u.parent = ?1",nativeQuery = true)
    Set<User> findChildren(Integer parent);

    User findByEmail(String email);

    boolean existsByName(String name);
}
