package com.sms.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * ClassName：SmsService
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/6/3 12:03
 * @version 1.0.0
 */
@Service
public class SmsService {


    public JSONObject getMessage(String phone) {
        JSONObject jsonObject = new JSONObject();
        try {

            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod("http://utf8.api.smschinese.cn/");
            //在头文件中设置转码
            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            String code = getCode(); //生成验证码
            System.out.println("验证码："+code);
            NameValuePair[] data = {new NameValuePair("Uid", "lihw"),// 注册的用户名
                    new NameValuePair("Key", "d41d8cd98f00b204e980"),// 注册成功后，登录网站后得到的密钥
                    new NameValuePair("smsMob", phone),// 接收人的手机号码
                    new NameValuePair("smsText", getMsgContent(code))};// 短信内容
            post.setRequestBody(data);
            client.executeMethod(post);
            int statusCode = post.getStatusCode();
            if (statusCode == 200) {
                //短信发送成功
                /*
                *
                * 自定义代码块
                * 比如设置Redis或者session保存验证码，用于输入的验证码进行校验的工作。
                *
                */
                jsonObject.put("status", 200);
            } else {
                jsonObject.put("status", 401);
            }

            post.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * MethodName: getCode
     * Description: 生成验证码，并获取
     *
     * @param
     * @return code
     * @author lihw
     * CreateTime 2020/6/3 12:20
     */
    public String getCode() {
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int) (Math.random() * 9);
        }
        return code;
    }

    public String getMsgContent(String code) {
        return "您用于lihw的网站的手机验证码是" + code + "，十分钟内有效。";
    }
}
