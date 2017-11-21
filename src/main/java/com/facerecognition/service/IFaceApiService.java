package com.facerecognition.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFaceApiService {

    float FaceRecognition(String filePathA, String filePathB);

    float FaceDetection(String filePath);

    String imgUpload( MultipartFile file) throws IOException;
}
