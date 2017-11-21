package com.facerecognition.service;


import com.facerecognition.domian.User;

/**
 * Ldap实现接口
 *
 * @author gaoyanlei
 * @since 2016/10/28
 */


public interface ILDAPService {
    /**
     * 提供sso整体接口验证用户
     *
     * @author gaoyanlei
     */
    public User verifyUser(String userName, String password);

    /**
     * ldap 验证接口
     *
     * @author gaoyanlei
     */
    public boolean checkLDAPUser(String userName, String password);

    /**
     * 按用户名查询
     *
     * @author gaoyanlei
     */
    public User findLdapUserInfo(String userName);

}
