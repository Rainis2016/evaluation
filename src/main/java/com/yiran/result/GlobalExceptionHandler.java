package com.yiran.result;

import com.yiran.entities.ReturnInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yiran on 17-1-23.
 */
@ControllerAdvice(basePackages = "com.yiran.controllers")
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = IMException.class)
//    @ResponseBody
//    public ReturnInfo<String> GlobalExceptionHandler(HttpServletRequest request, IMException iMException){
//        ReturnInfo<String> returnInfo = new ReturnInfo<>();
//        returnInfo.setRetCode(ReturnInfo.RETURN_FAIL);
//        returnInfo.setMessage(iMException.getMessage());
//        return returnInfo;
//    }
}
