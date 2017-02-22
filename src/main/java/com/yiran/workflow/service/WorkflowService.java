package com.yiran.workflow.service;

import com.yiran.constans.SysConstant;
import com.yiran.entities.Evaluation;
import com.yiran.entities.User;
import com.yiran.repositories.EvaluationRepository;
import com.yiran.repositories.UserRepository;
import com.yiran.result.IMException;
import com.yiran.workflow.constant.WorkflowConstant;
import com.yiran.workflow.entities.HistoryTask;
import com.yiran.workflow.entities.RunningTask;
import com.yiran.workflow.repositories.HistoryTaskRepository;
import com.yiran.workflow.repositories.RunningTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Yiran on 2017/2/7.
 */
@Service
public class WorkflowService {

    @Autowired
    private RunningTaskRepository runningTaskRepository;

    public void createWf(Long businessKey, String taskName, String status) throws IMException {
        Long count = runningTaskRepository.countByBusinessKey(businessKey);
        if (count == 0) {
            RunningTask runningTask = new RunningTask();
            runningTask.setBusinessKey(businessKey);
            runningTask.setTaskName(taskName);
            runningTask.setStatus(status);
            runningTaskRepository.save(runningTask);
        }
        if (count > 1) {
            throw new IMException("workflow is duplicate ,please check database");
        }
        if (count == 1) {
            throw new IMException("workflow has been created ,please check database");
        }
    }

    public void createPersontWf(Long businesskey) throws IMException {
        HistoryTask endTask = historyTaskRepository.findByBusinessKeyAndStatus(businesskey, WorkflowConstant.PERSON_END);
        if (endTask != null) {
            throw new IMException("person evaluation has been ended");
        }
        createWf(businesskey, "personWf", WorkflowConstant.PERSON_FILL_IN);
    }

    public void createDepartmentWf(Long businesskey) throws IMException {
        HistoryTask endTask = historyTaskRepository.findByBusinessKeyAndStatus(businesskey, WorkflowConstant.DEPARTMENT_END);
        if (endTask != null) {
            throw new IMException("department evaluation has been ended");
        }
        createWf(businesskey, "departmentWf", WorkflowConstant.DEPARTMENT_SCORE);
    }

    public void nextPersonWf(Long businessKey) throws IMException {
        updateWf(businessKey, WorkflowConstant.PERSON_WORKFLOW, false);
    }

    public void nextDepartmentWf(Long businessKey) throws IMException {
        updateWf(businessKey, WorkflowConstant.DEPARTMENT_WORKFLOW, false);
    }

    public void updateWf(Long businessKey, String[] workflowList, boolean isReverseWf) throws IMException {
        Long count = runningTaskRepository.countByBusinessKey(businessKey);
        if (count == 0) {
            throw new IMException("workflow has not created ,please check database");
        }
        if (count > 1) {
            throw new IMException("workflow is duplicate ,please check database");
        }
        if (count == 1) {
            RunningTask runningTask = runningTaskRepository.findByBusinessKey(businessKey);
            updateWfStatus(runningTask, workflowList, isReverseWf);
        }
    }

    @Autowired
    private HistoryTaskRepository historyTaskRepository;

    public void updateWfStatus(RunningTask runningTask, String[] workflowList, boolean isReverseWf) throws IMException {
        String status = runningTask.getStatus();
        Date now = new Date();
        if (StringUtils.isEmpty(status)) {
            throw new IMException("status is empty ,please check workflow");
        }
        for (int i = 0; i < workflowList.length-1; i++) {
            if(workflowList[i].equals(status)){
                HistoryTask historyTask = new HistoryTask();
                historyTask.setBusinessKey(runningTask.getBusinessKey());
                historyTask.setTaskName(runningTask.getTaskName());
                historyTask.setUpdatedAt(now);
                historyTask.setStatus(runningTask.getStatus());

                Evaluation evaluation = evaluationRepository.findOne(runningTask.getBusinessKey());
                if(i<workflowList.length-2){
                    runningTask.setStatus(workflowList[i+1]);
                    evaluation.setStatus(workflowList[i+1]);
                    runningTask.setUpdatedAt(now);
                    runningTaskRepository.save(runningTask);
                }
                if(i==workflowList.length-2){
                    runningTaskRepository.delete(runningTask);
                    historyTask.setStatus(workflowList[workflowList.length-1]);
                    evaluation.setStatus(workflowList[workflowList.length-1]);
                }
                historyTaskRepository.save(historyTask);
                evaluationRepository.save(evaluation);

            }
        }
    }

    public String getStatus(Long personBusinessKey) throws IMException {
        String personStatus = getPersonStatus(personBusinessKey);
        if (WorkflowConstant.PERSON_SUBMIT.equals(personStatus)) {
            String departmentStatus = getDepartmentStatus(getDepartmentBusinessKey(personBusinessKey));
            if (WorkflowConstant.DEPARTMENT_UNINITIATED.equals(departmentStatus)) {
                return WorkflowConstant.PERSON_SUBMIT;
            } else {
                return departmentStatus;
            }
        }
        return personStatus;
    }

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private UserRepository userRepository;

    public Long getDepartmentBusinessKey(Long personBusinessKey) throws IMException {
        Evaluation evaluation = evaluationRepository.findOne(personBusinessKey);
        if (evaluation == null) {
            throw new IMException("evaluation has not created");
        }
        String workCode = evaluation.getWorkCode();
        if (StringUtils.isEmpty(workCode)) {
            throw new IMException("workCode is empty");
        }
        User user = userRepository.findByWorkCode(workCode);
        if (user == null) {
            throw new IMException("current user is not in database");
        }
        Evaluation departmentEvaluation = evaluationRepository.findByWorkCodeAndYear(user.getBranchCode(), SysConstant.CURRENT_YEAR);
        if (departmentEvaluation == null) {
            throw new IMException("deparment evaluation has not created");
        }
        return departmentEvaluation.getId();
    }

    public String getPersonStatus(Long personBusinessKey) {//个人考核的id
        RunningTask runningTask = runningTaskRepository.findByBusinessKey(personBusinessKey);
        HistoryTask endTask = historyTaskRepository.findByBusinessKeyAndStatus(personBusinessKey, WorkflowConstant.PERSON_END);
        if (runningTask == null && endTask == null) {
            return WorkflowConstant.PERSON_UNINITIATED;
        } else if (runningTask == null && endTask != null) {
            return WorkflowConstant.PERSON_END;
        }
        return runningTask.getStatus();
    }

    public String getDepartmentStatus(Long departmentBusinessKey) {//部门考核的id
        RunningTask runningTask = runningTaskRepository.findByBusinessKey(departmentBusinessKey);
        HistoryTask endTask = historyTaskRepository.findByBusinessKeyAndStatus(departmentBusinessKey, WorkflowConstant.DEPARTMENT_END);
        if (runningTask == null && endTask == null) {
            return WorkflowConstant.DEPARTMENT_UNINITIATED;
        } else if (runningTask == null && endTask != null) {
            return WorkflowConstant.DEPARTMENT_END;
        }
        return runningTask.getStatus();
    }

    public void deletePersonWf(Long personBusinessKey) {
        List<HistoryTask> historyTaskList = historyTaskRepository.findByBusinessKey(personBusinessKey);
        RunningTask runningTask = runningTaskRepository.findByBusinessKey(personBusinessKey);
        if (historyTaskList != null) {
            historyTaskRepository.delete(historyTaskList);
        }
        if (runningTask != null) {
            runningTaskRepository.delete(runningTask);
        }
    }

    public void deleteDepartmentWf(Long departmentBusinessKey) {
        deletePersonWf(departmentBusinessKey);
    }

    public void backPersonWf(Long businessKey) throws IMException {
        backWf(businessKey, WorkflowConstant.PERSON_WORKFLOW_REVERSE);
    }

    public void backDepartmentWf(Long businessKey) throws IMException {
        backWf(businessKey, WorkflowConstant.DEPARTMENT_WORKFLOW_REVERSE);
    }

    public void backWf(Long businessKey, String[] workflowList_revese) throws IMException {
        updateWf(businessKey, workflowList_revese, true);
    }

}
