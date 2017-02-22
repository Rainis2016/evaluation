package com.yiran.workflow.constant;

/**
 * Created by Yiran on 2017/2/7.
 */
public class WorkflowConstant {

    public static final String PERSON_UNINITIATED = "person_uninitiated";
    public static final String PERSON_END = "person_end";
    public static final String DEPARTMENT_UNINITIATED = "department_uninitiated";
    public static final String DEPARTMENT_END = "department_end";

    public static final String PERSON_FILL_IN = "person_fill_in";
    public static final String PERSON_SUBMIT = "person_submit";
    public static final String PERSON_SIGNATURE = "person_signature";

    public static final String DEPARTMENT_SCORE = "department_score";
    public static final String DEPARTMENT_RANK = "department_rank";
    public static final String DEPARTMENT_FEEDBACK = "department_feedback";

    public static final String[] PERSON_WORKFLOW = {PERSON_UNINITIATED,PERSON_FILL_IN, PERSON_SUBMIT, PERSON_SIGNATURE,PERSON_END};
    public static final String[] DEPARTMENT_WORKFLOW = {DEPARTMENT_UNINITIATED,DEPARTMENT_SCORE, DEPARTMENT_RANK, DEPARTMENT_FEEDBACK,DEPARTMENT_END};

    public static final String[] PERSON_WORKFLOW_REVERSE = {PERSON_END,PERSON_SIGNATURE, PERSON_SUBMIT, PERSON_FILL_IN,PERSON_UNINITIATED};
    public static final String[] DEPARTMENT_WORKFLOW_REVERSE = {DEPARTMENT_END,DEPARTMENT_FEEDBACK, DEPARTMENT_RANK, DEPARTMENT_SCORE,DEPARTMENT_UNINITIATED};

}
