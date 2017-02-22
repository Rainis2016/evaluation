package com.yiran;

import com.yiran.repositories.AnswerRepository;
import com.yiran.repositories.EvaluationRepository;
import com.yiran.result.IMException;
import com.yiran.service.EvaluationService;
import com.yiran.workflow.constant.WorkflowConstant;
import com.yiran.workflow.service.WorkflowService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;

import static java.lang.Thread.sleep;


/**
 * Created by Yiran on 2017/2/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Test
    public void saveAnswerTest(){
//        evaluationService.save();
    }

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Test
    public void saveEvaluationTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    }


    @Autowired
    private WorkflowService workflowService;

    @Test
    public void workflowTest() throws IMException, InterruptedException {
        long personBusinessKey = 14L;
        long departmentBusinesskey = 15L;

        workflowService.deletePersonWf(personBusinessKey);
        workflowService.deleteDepartmentWf(departmentBusinesskey);

        Assert.assertEquals(WorkflowConstant.PERSON_UNINITIATED,workflowService.getStatus(personBusinessKey));

        workflowService.createPersontWf(personBusinessKey);
        Assert.assertEquals(WorkflowConstant.PERSON_FILL_IN,workflowService.getStatus(personBusinessKey));

        workflowService.backPersonWf(personBusinessKey);
        Assert.assertEquals(WorkflowConstant.PERSON_UNINITIATED,workflowService.getStatus(personBusinessKey));

        workflowService.createPersontWf(personBusinessKey);
        Assert.assertEquals(WorkflowConstant.PERSON_FILL_IN,workflowService.getStatus(personBusinessKey));

        workflowService.nextPersonWf(personBusinessKey);
        Assert.assertEquals(WorkflowConstant.PERSON_SUBMIT,workflowService.getStatus(personBusinessKey));

        workflowService.backPersonWf(personBusinessKey);
        Assert.assertEquals(WorkflowConstant.PERSON_FILL_IN,workflowService.getStatus(personBusinessKey));

        workflowService.nextPersonWf(personBusinessKey);
        Assert.assertEquals(WorkflowConstant.PERSON_SUBMIT,workflowService.getStatus(personBusinessKey));

        workflowService.createDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.DEPARTMENT_SCORE,workflowService.getStatus(personBusinessKey));

        workflowService.backDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.PERSON_SUBMIT,workflowService.getStatus(personBusinessKey));

        workflowService.createDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.DEPARTMENT_SCORE,workflowService.getStatus(personBusinessKey));

        workflowService.nextDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.DEPARTMENT_RANK,workflowService.getStatus(personBusinessKey));

        workflowService.backDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.DEPARTMENT_SCORE,workflowService.getStatus(personBusinessKey));

        workflowService.nextDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.DEPARTMENT_RANK,workflowService.getStatus(personBusinessKey));

        workflowService.nextDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.DEPARTMENT_FEEDBACK,workflowService.getStatus(personBusinessKey));

        workflowService.backDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.DEPARTMENT_RANK,workflowService.getStatus(personBusinessKey));

        workflowService.nextDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.DEPARTMENT_FEEDBACK,workflowService.getStatus(personBusinessKey));

        workflowService.nextDepartmentWf(departmentBusinesskey);
        Assert.assertEquals(WorkflowConstant.DEPARTMENT_END,workflowService.getStatus(personBusinessKey));

        workflowService.nextPersonWf(personBusinessKey);
        Assert.assertEquals(WorkflowConstant.PERSON_SIGNATURE,workflowService.getStatus(personBusinessKey));

        workflowService.nextPersonWf(personBusinessKey);
        Assert.assertEquals(WorkflowConstant.PERSON_END,workflowService.getStatus(personBusinessKey));
    }
}
