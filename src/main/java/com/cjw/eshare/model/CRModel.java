package com.cjw.eshare.model;

import com.cjw.eshare.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cj.w
 * @program: eshare
 * @description: 公共返回对象
 * @create: 2021/2/27 15:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CRModel {
    private long code;
    private String message;
    private Object object;

    /**
     * 成功返回结果
     * @param message 返回信息
     * @param obj 返回对象
     * @return
     */
    public static CRModel success(String message, Object obj) {
        return new CRModel(200,message,obj);
    }

    /**
     * 成功返回结果
     * @param message 返回信息
     * @return
     */
    public static CRModel success(String message) {
        return new CRModel(200,message,null);
    }

    /**
     * 失败返回结果
     * @param message 返回信息
     * @return
     */
    public static CRModel error(String message) {
        return new CRModel(ErrorCode.DEFAULT_ERR, message, null);
    }

    /**
     * 失败返回结果
     * @param message 返回信息
     * @param obj 返回对象
     * @return
     */
    public static CRModel error(String message, Object obj) {
        return new CRModel(ErrorCode.DEFAULT_ERR, message, obj);
    }

    /**
     * 失败返回结果
     * @param code  错误码
     * @param message 错误信息
     * @return
     */
    public static CRModel error(long code, String message) {
        return new CRModel(code, message, null);
    }


}
