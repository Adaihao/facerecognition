package com.facerecognition.repository;

import com.facerecognition.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author adaiaho
 * @params
 * @since 2017/11/9
 */
public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {
    User findByUserName(String username);

    User findByUserId(Long userId);

}