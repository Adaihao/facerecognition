package com.facerecognition.utils;

import com.cloudzone.cloudoss.api.common.PropertiesConst;
import com.cloudzone.cloudoss.api.open.OSS;
import com.cloudzone.cloudoss.api.open.factory.OSSFactory;

import java.util.Properties;

/**
 * OSS Client
 * 
 * @author adaihao
 * @since 2017/11/9 
 */
public class OSSClientUtil {
    private static OSS OSSClient;

    public static OSS getOSSClient() {
        if (OSSClient == null) {
            synchronized (OSSClientUtil.class) {
                if (OSSClient == null) {
                    OSSClient = CreateOSSClient();
                }
            }
        }
        return OSSClient;
    }

    public static OSS CreateOSSClient() {
        Properties properties = new Properties();
        // APP_META_NAME 为申请存储时业务名称
        properties.setProperty(PropertiesConst.Keys.APP_META_NAME, "jcpt-oss-test-500");
        // AUTH_KEY 为业务名称对应的AUTH_KEY
        properties.setProperty(PropertiesConst.Keys.AUTH_KEY,
                "0cabcec6977384564bc595df76102cf68");
        // 创建对象存储服务
        OSSClient = OSSFactory.createOSS(properties);
        return OSSClient;
    }
}
