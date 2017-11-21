package com.facerecognition.service.impl;

import com.cloudzone.cloudoss.api.constant.ContentType;
import com.cloudzone.cloudoss.api.open.OSS;
import com.facerecognition.arcsoftAPI.ASVLOFFSCREEN;
import com.facerecognition.arcsoftAPI.FaceInfo;
import com.facerecognition.utils.OSSClientUtil;
import com.facerecognition.utils.SystemUtil;
import com.facerecognition.service.IFaceApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author luoji
 * @params
 * @since 2017/11/4 0004
 */
@Service
public class FaceApiServiceImpl implements IFaceApiService {

    public float FaceRecognition(String filePathA, String filePathB) {
        ASVLOFFSCREEN inputImgA = null;
        ASVLOFFSCREEN inputImgB = null;
        if (filePathA != null && filePathB != null) {
            inputImgA = SystemUtil.loadImage(filePathA);
            inputImgB = SystemUtil.loadImage(filePathB);
        }
        float value = SystemUtil.compareFaceSimilarity(inputImgA, inputImgB);
        System.out.println(String.format("similarity between faceA and faceB is %f", value));
        return value;
    }

    @Override
    public float FaceDetection(String filePath) {
        // load Image Data
        ASVLOFFSCREEN inputImg = new ASVLOFFSCREEN();
        if (filePath != null) {
            inputImg = SystemUtil.loadImage(filePath);
        }
        // do Face Detect
        FaceInfo[] faceInfos = SystemUtil.doFaceDetection(inputImg);
        for (int i = 0; i < faceInfos.length; i++) {
            FaceInfo rect = faceInfos[i];
            System.out.println(String.format("%d (%d %d %d %d) orient %d", i, rect.left, rect.top, rect.right, rect.bottom, rect.orient));
        }

        System.out.println("#####################################################");
        return faceInfos.length;
    }

    @Override
    public String imgUpload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ("文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        OSS OSSClient = OSSClientUtil.getOSSClient();
        String filePath = OSSClient.putObject(fileName, file.getInputStream(), ContentType.MULTIPART_FORM_DATA);
//        AFR_FSDK_FACEMODEL faceFeature = SystemUtil.getFaceFeature(filePath);
//        SystemUtil.storeFaceFeature(faceFeature,filePath);
        return filePath;
    }
}
