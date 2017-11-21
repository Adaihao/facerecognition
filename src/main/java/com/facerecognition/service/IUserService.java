package com.facerecognition.service;

import com.facerecognition.domian.LoginUser;
import com.facerecognition.domian.User;
import com.facerecognition.domian.vo.RegisterUserVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * sso用户
 *
 * @author adaiaho
 * @since 2016/10/25
 */
public interface IUserService {
    User register(RegisterUserVO registerUserVO) throws Exception;

    LoginUser login(MultipartFile file) throws Exception;

    List<User> findall() ;

}
