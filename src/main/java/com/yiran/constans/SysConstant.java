package com.yiran.constans;

import lombok.Data;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yiran on 17-1-22.
 */
public class SysConstant {
    public static final String CURRENT_YEAR = "2017";
    public static final String[] HISTORY_YEARS = {"2014","2015","2016"};

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_LEADER = "ROLE_LEADER";
    public static final String ROLE_HR = "ROLE_HR";
    public static final String ROLE_STAFF = "ROLE_STAFF";
    public static final String ROLE_BANK = "ROLE_BANK";
    public static final String ROLE_AGENT = "ROLE_AGENT";

    public static final Integer RETURN_SUCCESS = 0;
    public static final Integer RETURN_FAIL = -1;

    public static final String COMMENT_LEADER = "COMMENT_LEADER";
    public static final String COMMENT_STAFF = "COMMENT_STAFF";
    public static final String REWARD_LEADER = "REWARD_LEADER";
    public static final String REWARD_HR = "REWARD_HR";

}

