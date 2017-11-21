package com.facerecognition.service.impl;

import com.facerecognition.domian.User;
import com.facerecognition.repository.UserRepository;
import com.facerecognition.service.ILDAPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;


@Service
@PropertySource({"classpath:ldap.properties"})
public class LDAPServiceImpl implements ILDAPService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LDAPServiceImpl.class);
    static DirContext dc = null;

    @Value("${ldap.url}")
    public String ldapUrl;

    @Value("${ldap.factoryPath}")
    public String ldapFactoryPath;

    @Value("${ldap.base}")
    public String ldapBase;

    @Value("${ldap.domain}")
    public String ldapDomain;

    @Value("${ldap.userName}")
    public String userName;

    @Value("${ldap.password}")
    public String password;

    @Autowired
    private UserRepository userRepository;


    @Override
    public User verifyUser(String userName, String password) {
        LOGGER.info("ldap verifyUser userName={}", userName);
        try {
            boolean check = this.checkLDAPUser(userName, password);
            LOGGER.info("ldap login result={}", check);
            if (!check) {
                return null;
            }

            User user = userRepository.findByUserName(userName);

            user = this.findLdapUserInfo(userName);
            if (user == null) {
                return null;
            }
            user.setUserName(userName);
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            LOGGER.info("verifyUser login exception. msg={}", e.getMessage(), e);
            return null;
        } finally {
            LOGGER.info("verifyUser login finally.");
            this.close();
        }
    }


    @Override
    public boolean checkLDAPUser(String userName, String password) {
        try {
            long start = System.currentTimeMillis();
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, ldapFactoryPath);
            env.put(Context.PROVIDER_URL, ldapUrl);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, ldapDomain + userName);
            env.put(Context.SECURITY_CREDENTIALS, password);
            dc = new InitialDirContext(env); // 验证信息
            long end = System.currentTimeMillis();
            LOGGER.info("ldap login successful.  userName={}, useTimes={}ms", userName, (end - start));
            return true;
        } catch (Exception e) {
            LOGGER.error("ldap login error. msg={}", e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {

    }


    /**
     * 关闭Ldap连接
     */
    public void close() {
        if (dc != null) {
            try {
                dc.close();
            } catch (NamingException e) {
                LOGGER.info("namingException in close():" + e);
            }
        }
    }


    /**
     * 按用户名查询
     *
     * @throws NamingException
     */
    public User findLdapUserInfo(String userName) {
        LOGGER.info("ldap verify userName={}", userName);
        this.checkLDAPUser(this.userName, password);
        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String searchFilter = "sAMAccountName=" + userName;
        String returnedAtts[] = {"name", "distinguishedname", "displayName", "sAMAccountName"};
        searchCtls.setReturningAttributes(returnedAtts);
        try {
            NamingEnumeration answer = dc.search(ldapBase, searchFilter, searchCtls);
            if (answer == null || answer.equals(null)) {
                LOGGER.info("answer is null. ldapBase={}|searchFilter={}|searchScope={}", ldapBase,
                        searchFilter, SearchControls.SUBTREE_SCOPE);
            } else {
                while (answer.hasMoreElements()) {
                    SearchResult sr = (SearchResult) answer.next();
                    Attributes attributes = sr.getAttributes();
                    if (attributes != null) {
                        try {
                            User user = new User();
                            user.setOrganization(attributes.get("distinguishedname").get().toString());
                            user.setBindDn(attributes.get("distinguishedname").get().toString());
                            user.setUserTitle(attributes.get("displayName").get().toString());
                            user.setUserName(attributes.get("sAMAccountName").get().toString());
//                            user.setBase(ldapBase);
                            return user;
                        } catch (NamingException e) {
                            LOGGER.error("build ssoUser exception. msg={}", e.getMessage(), e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("throw exception. msg={}", e.getMessage(), e);
        } finally {
            this.close();
        }
        return null;
    }

}
