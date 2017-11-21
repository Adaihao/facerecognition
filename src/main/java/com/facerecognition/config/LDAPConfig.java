package com.facerecognition.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @author adaihao
 * @params
 * @since 2017/11/4 0004
 */
@PropertySource({"classpath:ldap.properties"})
public class LDAPConfig {
    @Value("${ldap.url}")
    private String url;
    @Value("${ldap.factoryPath}")
    private String factoryPath;
    @Value("${ldap.base}")
    private String base;
    @Value("${ldap.domain}")
    private String domain;
    @Value("${ldap.userName}")
    private String userName;
    @Value("${ldap.password}")
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFactoryPath() {
        return factoryPath;
    }

    public void setFactoryPath(String factoryPath) {
        this.factoryPath = factoryPath;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
