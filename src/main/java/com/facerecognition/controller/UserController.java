package com.facerecognition.controller;

import com.facerecognition.domian.vo.RegisterUserVO;
import com.facerecognition.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author adaihao
 * @since 2017/11/9
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object register(@RequestBody RegisterUserVO registerUserVO) throws Exception {
        return this.ok(userService.register(registerUserVO));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam(value = "file") MultipartFile file) throws Exception {
        return this.ok(userService.login(file));
    }
}