package com.facerecognition.controller;

import com.facerecognition.domian.Recognition;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author adaihao
 * @since 2017/11/9
 */
@Controller
@RequestMapping("/face")
public class FaceApiController extends BaseController {
    @Autowired
    private com.facerecognition.service.IFaceApiService IFaceApiService;

    @RequestMapping(value = "/recognition", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object recognition(@RequestBody Recognition recognition, HttpServletRequest request, HttpServletResponse response) {
        return this.ok(IFaceApiService.FaceRecognition(recognition.getFilePathA(), recognition.getFilePathB()));
    }

    @RequestMapping(value = "/detection", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object detection(@RequestBody Recognition recognition, HttpServletRequest request, HttpServletResponse response) {
        return this.ok(IFaceApiService.FaceDetection(recognition.getFilePathA()));
    }

    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object imgUpload(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ("文件不能为空");
        }
        return this.ok(IFaceApiService.imgUpload(file));
    }
}
