package com.sms.controller;

import com.alibaba.fastjson.JSONObject;
import com.sms.service.QQMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName：QQMailController
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/6/3 16:12
 * @version 1.0.0
 */
@RestController
public class QQMailController {

    @Autowired
    private QQMailService qqMailService;


    //QQ邮箱发送验证码
    @PostMapping("/sendQQMail")
    public JSONObject sendQQMail(String toMailUser){
        return qqMailService.sendQQMail(toMailUser);
    }
}
