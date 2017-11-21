package com.facerecognition.domian;


import javax.persistence.*;
import java.util.Date;


/**
 * @author adaiaho
 * @since 2016/10/25
 */
@Entity
@Table(name = "users")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "user_name", nullable = true, length = 500)
    private String userName;

    @Column(name = "user_title", nullable = true, length = 500)
    private String userTitle;

    @Column(name = "organization", nullable = true, length = 500)
    private String organization;

    @Column(name = "bind_dn", nullable = true, length = 500)
    private String bindDn;

    @Column(name = "this_login_date", nullable = true, length = 500)
    private Date thisLoginDate;

    @Column(name = "last_login_date", nullable = true, length = 500)
    private Date lastLoginDate;

    public Date getThisLoginDate() {
        return thisLoginDate;
    }

    public void setThisLoginDate(Date thisLoginDate) {
        this.thisLoginDate = thisLoginDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Column(name = "face_img_path", nullable = true, length = 500)
    private String faceImgPath;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFaceImgPath() {
        return faceImgPath;
    }

    public void setFaceImgPath(String faceImgPath) {
        this.faceImgPath = faceImgPath;
    }

    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getOrganization() {
        return organization;
    }


    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getBindDn() {
        return bindDn;
    }


    public void setBindDn(String bindDn) {
        this.bindDn = bindDn;
    }


    //    public String getBase() {
    //        return base;
    //    }
    //
    //
    //    public void setBase(String base) {
    //        this.base = base;
    //    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }
}
