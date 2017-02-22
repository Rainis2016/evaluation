package com.yiran.result;

/**
 * Created by Yiran on 17-1-23.
 */
public class IMException extends Exception{

    public IMException (String ExceptionMessage){
        super(ExceptionMessage);
    }

    @Override
    public String getMessage(){
        String className = super.getStackTrace()[0].getClassName();
        String methodName = super.getStackTrace()[0].getMethodName();
        return "["+className+"]  ["+methodName+"]  "+super.getMessage();
    }
}
