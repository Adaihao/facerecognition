package com.facerecognition.service.impl;

import com.facerecognition.arcsoftAPI.AFR_FSDK_FACEMODEL;
import com.facerecognition.arcsoftAPI.ASVLOFFSCREEN;
import com.facerecognition.domian.User;
import com.facerecognition.domian.vo.RegisterUserVO;
import com.facerecognition.repository.UserRepository;
import com.facerecognition.service.ILDAPService;
import com.facerecognition.utils.FileUtils;
import com.facerecognition.utils.SystemUtil;
import com.facerecognition.domian.LoginUser;
import com.facerecognition.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author adaiaho
 * @params
 * @since 2017/11/4 0004
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Autowired
    private ILDAPService ldapService;

    private HashMap<Long, AFR_FSDK_FACEMODEL> facemodels;


    @Override
    public User register(RegisterUserVO registerUserVO) throws Exception {
        User user = userRepository.findByUserName(registerUserVO.getUserName());
        if (user == null) {
            user = ldapService.findLdapUserInfo(registerUserVO.getUserName());
            if (user == null) {
                throw new Exception("LDAP 查询不到用户");
            }
            user.setFaceImgPath(registerUserVO.getFaceImgPath());
            userRepository.save(user);
            user.getUserId();
            AFR_FSDK_FACEMODEL faceFeature = SystemUtil.getFaceFeature(registerUserVO.getFaceImgPath());
            SystemUtil.storeFaceFeature(faceFeature, user.getUserId().toString());
            HashMap<Long, AFR_FSDK_FACEMODEL> faceFeatureMap = getFacemodels();
        }
        return user;
    }

    @Override
    public LoginUser login(MultipartFile file) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LoginUser loginUser = new LoginUser();
        float score = 0;
        if (file != null) {
            HashMap<Long, AFR_FSDK_FACEMODEL> faceFeatureMap = getFacemodels();
            Iterator iter = faceFeatureMap.entrySet().iterator();
            List<User> userList = new ArrayList<User>();
            while (iter.hasNext()) {
                ASVLOFFSCREEN inputImg = SystemUtil.loadImage(file);
                AFR_FSDK_FACEMODEL inImgfaceFeature = SystemUtil.getFaceFeature(inputImg);
                Map.Entry<Long, AFR_FSDK_FACEMODEL> entry = (Map.Entry<Long, AFR_FSDK_FACEMODEL>) iter.next();
                AFR_FSDK_FACEMODEL val = entry.getValue();
                score = SystemUtil.compareFaceSimilarity(inImgfaceFeature, val);
                if (score * 100 > 60) {
                    userList.add(userRepository.findByUserId(entry.getKey()));
                    break;
                }
            }
            if (userList.size() > 0) {
                User user = userList.get(0);
                if (user.getThisLoginDate() != null) {
                    loginUser.setLastLoginDate(formatter.format(user.getThisLoginDate()));
                } else {
                    loginUser.setLastLoginDate("");
                }
                user.setLastLoginDate(user.getThisLoginDate());
                user.setThisLoginDate(new Date());
                userRepository.saveAndFlush(user);

                loginUser.setUser(user);
                loginUser.setLoginStatus(true);
                loginUser.setScore(score);
                loginUser.setThisLoginDate(formatter.format(new Date()));
                loginUser.setScore(score);
                return loginUser;
            } else {
                loginUser.setLoginStatus(false);
                loginUser.setScore(score);
                return loginUser;
            }
        } else {
            return null;
        }
    }

    private HashMap<Long, AFR_FSDK_FACEMODEL> loadFaceFeature() throws Exception {
        List<User> users = userRepository.findAll();
        facemodels = new HashMap<Long, AFR_FSDK_FACEMODEL>();
        for (User user : users) {
            byte[] feature = FileUtils.toByteArray2(SystemUtil.storePathRootDir + "//" + user.getUserId() + SystemUtil.suffix);
            AFR_FSDK_FACEMODEL faceFeature = AFR_FSDK_FACEMODEL.fromByteArray(feature);
            facemodels.put(user.getUserId(), faceFeature);
        }
        return facemodels;
    }

    @Override
    public List<User> findall() {
        return userRepository.findAll();
    }

    public HashMap<Long, AFR_FSDK_FACEMODEL> getFacemodels() throws Exception {
        //        if (facemodels == null) {
        //            synchronized (this) {
        //                if (facemodels == null) {
        return loadFaceFeature();
        //                }
        //            }
        //        }
        //        return facemodels;
    }
}
