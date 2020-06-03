package com.sms.controller;

import com.alibaba.fastjson.JSONObject;
import com.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName：SmsController
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/6/3 12:29
 * @version 1.0.0
 */
@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/getCode")
    public JSONObject getCode(@RequestParam("phone") String phone) {
        return smsService.getMessage(phone);
    }

}
