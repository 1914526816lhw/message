package com.sms.util;

/**
 * ClassName：CodeUtil
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/6/3 16:22
 * @version 1.0.0
 */
public class CodeUtil {
    /**
     * MethodName: getCode
     * Description: 生成验证码
     * @author lihw
     * CreateTime 2020/6/3 16:22
     * @param
     * @return code
     */
    public static String getCode() {
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int) (Math.random() * 9);
        }
        return code;
    }
}
