package com.yiran.result;



import com.yiran.entities.Answer;
import com.yiran.entities.BaseModel;
import com.yiran.entities.ReturnInfo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.yiran.constans.SysConstans.RETURN_FAIL;
import static com.yiran.constans.SysConstans.RETURN_SUCCESS;

/**
 * Created by Yiran on 17-1-23.
 */

@ControllerAdvice(basePackages = "com.yiran.controllers")
public class JsonResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("data", body);
        dataMap.put("retCode", RETURN_SUCCESS);
        return dataMap;
    }

    @ExceptionHandler(value = IMException.class)
    @ResponseBody
    public ReturnInfo<String> returnExceptionHandler(HttpServletRequest request, IMException iMException){
        ReturnInfo<String> returnInfo = new ReturnInfo<>();
        returnInfo.setRetCode(RETURN_FAIL);
        returnInfo.setMessage(iMException.getMessage());
        return returnInfo;
    }
}
