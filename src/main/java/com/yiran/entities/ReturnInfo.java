package com.yiran.entities;

import lombok.Getter;
import lombok.Setter;

import static com.yiran.constans.SysConstant.RETURN_SUCCESS;

/**
 * Created by Yiran on 17-1-23.
 */
@Getter
@Setter
public class ReturnInfo<T> {
    private Integer retCode = RETURN_SUCCESS;
    private String message;
}
