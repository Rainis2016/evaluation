package com.yiran.service;

import com.yiran.constans.SysConstant;
import com.yiran.entities.Answer;
import com.yiran.entities.Comment;
import com.yiran.entities.Evaluation;
import com.yiran.entities.User;
import com.yiran.repositories.AnswerRepository;
import com.yiran.repositories.CommentRepository;
import com.yiran.repositories.EvaluationRepository;
import com.yiran.repositories.UserRepository;
import com.yiran.result.IMException;
import com.yiran.workflow.constant.WorkflowConstant;
import com.yiran.workflow.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Yiran on 2017/2/4.
 */
@Service
public class EvaluationService {

    public void save(User user, HttpServletRequest request) throws IMException {
        String role = user.getRole();
        if ("ROLE_STAFF".equals(role)) {
            staffSave(request);
        } else if ("ROLE_BANK".equals(role)) {
            bankSave(request);
        } else if ("ROLE_AGENT".equals(role)) {
            agentSave(request);
        } else if ("ROLE_LEADER".equals(role)) {
            leaderSave(request);
        } else if ("ROLE_HR".equals(role)) {
            hrSave(request);
        } else if ("ROLE_ADMIN".equals(role)) {
            adminSave(request);
        }
    }

    @Autowired
    private WorkflowService workflowService;

    public void submitPersonWf(User user, HttpServletRequest request) throws IMException {
        save(user,request);
        Long evaluationId = getEvalationId(request);
        String status = workflowService.getStatus(evaluationId);
        if(WorkflowConstant.PERSON_UNINITIATED.equals(status)){
            workflowService.createPersontWf(evaluationId);//开启管理员页面后注释掉此行
        }
        workflowService.nextPersonWf(evaluationId);
    }

    public void submitDepartmentWf(String dpCode){

    }

    private Long getEvalationId(HttpServletRequest request) throws IMException {
        String workCode = request.getParameter("workCode");
        if (StringUtils.isEmpty(workCode)) {
            throw new IMException("workCode is Empty, please check HttpServletRequest");
        }
        String year = request.getParameter("year");
        if (StringUtils.isEmpty(year)) {
            throw new IMException("year is Empty, please check HttpServletRequest");
        }
        Evaluation evaluation = evaluationRepository.findByWorkCodeAndYear(workCode, year);
        return evaluation.getId();
    }


    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Transactional
    private void staffSave(HttpServletRequest request) throws IMException {
        Long count = getEvalationCount(request);
        if (count == 0) {
            createEvaluation(request);
        } else if (count > 1) {
            throw new IMException("Curerent user's evalutation record for this year is duplicate, please check database");
        } else if (count == 1) {
            updateEvaluation(request);
        }
    }

    private Long getEvalationCount(HttpServletRequest request) throws IMException {
        String workCode = request.getParameter("workCode");
        if (StringUtils.isEmpty(workCode)) {
            throw new IMException("workCode is Empty, please check HttpServletRequest");
        }
        String year = request.getParameter("year");
        if (StringUtils.isEmpty(year)) {
            throw new IMException("year is Empty, please check HttpServletRequest");
        }
        Long count = evaluationRepository.countByWorkCodeAndYear(workCode, year);
        return count;
    }

    private void createEvaluation(HttpServletRequest request) throws IMException {
        String workCode = request.getParameter("workCode");
        if (StringUtils.isEmpty(workCode)) {
            throw new IMException("workCode is Empty, please check HttpServletRequest");
        }
        Evaluation evaluation = new Evaluation();
        evaluation.setYear(SysConstant.CURRENT_YEAR);
        evaluation.setWorkCode(workCode);
        evaluation.setAnswer1_Id(saveAnswerAndReturnId("answer1", request));
        evaluation.setAnswer2_Id(saveAnswerAndReturnId("answer2", request));
        evaluation.setAnswer3_Id(saveAnswerAndReturnId("answer3", request));
        evaluation.setAnswer4_Id(saveAnswerAndReturnId("answer4", request));
        evaluationRepository.save(evaluation);
    }


    private Long saveAnswerAndReturnId(String answerName, HttpServletRequest request) throws IMException {
        String answerContent = request.getParameter(answerName);
        if (StringUtils.isEmpty(answerContent)) {
            throw new IMException("answerContent is Empty, please check HttpServletRequest");
        }
        Answer answer = new Answer();
        answer.setAnswer(answerContent);
        answerRepository.save(answer);
        return answer.getId();
    }

    public void updateEvaluation(HttpServletRequest request) throws IMException {
        String workCode = request.getParameter("workCode");
        if (StringUtils.isEmpty(workCode)) {
            throw new IMException("workCode is Empty, please check HttpServletRequest");
        }
        String year = request.getParameter("year");
        if (StringUtils.isEmpty(year)) {
            throw new IMException("year is Empty, please check HttpServletRequest");
        }
        Evaluation evaluation = evaluationRepository.findByWorkCodeAndYear(workCode, year);
        updateAnswers(evaluation, request);
        updateComments(evaluation, request);

        evaluationRepository.save(evaluation);

    }

    private void updateAnswers(Evaluation evaluation, HttpServletRequest request) throws IMException {
        String[] parameterNames = {"answer1", "answer2", "answer3", "answer4"};
        for (String parameterName : parameterNames) {
            updateAnswer(evaluation, request, parameterName);
        }
    }

    private String firstUpperCase(String str) {
        if (str.length() == 0) {
            return str;
        } else if (str.length() == 1) {
            return str.toUpperCase();
        } else if (str.length() > 1) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }

    private void updateAnswer(Evaluation evaluation, HttpServletRequest request, String parameterName) throws IMException {
        String answerContent = request.getParameter(parameterName);
        try {
            Long id = (Long) evaluation.getClass().getMethod("get" + firstUpperCase(parameterName) + "_Id", (Class<?>[]) null).invoke(evaluation, (Object[]) null);
            Answer answer = new Answer();
            if (id != null) {
                Answer answerTemp = answerRepository.findOne(id);
                if (answerTemp != null) {
                    answer = answerTemp;
                }
            }
            answer.setAnswer(answerContent);
            answerRepository.save(answer);
            evaluation.getClass().getMethod("set" + firstUpperCase(parameterName) + "_Id", Long.class).invoke(evaluation, answer.getId());
        } catch (Exception e) {
            throw new IMException("getAnserId or setAnswerId may occur exception, updateAnswer fail");
        }
    }

    private void updateComments(Evaluation evaluation, HttpServletRequest request) throws IMException {
        String[] parameterNames = {"leaderComment", "staffComment", "leaderReward_Punish", "hrReward_Punish"};
        for (String parameterName : parameterNames) {
            updateComment(evaluation, request, parameterName);
        }
    }

    @Autowired
    private CommentRepository commentRepository;

    private void updateComment(Evaluation evaluation, HttpServletRequest request, String parameterName) throws IMException {
        String commentContent = request.getParameter(parameterName);
        String commentSign = request.getParameter(parameterName+"Sign");
        String commentSignDate = request.getParameter(parameterName+"SignDate");
        Comment comment = new Comment();
        try {
            Long id = (Long) evaluation.getClass().getMethod("get" + firstUpperCase(parameterName) + "_Id", (Class<?>[]) null).invoke(evaluation, (Object[]) null);
            if (id != null) {
                Comment commentTemp = commentRepository.findOne(id);
                if (commentTemp != null) {
                    comment = commentTemp;
                }
            }
            comment.setComment(commentContent);
            comment.setSignName(commentSign);
            comment.setSignDate(commentSignDate);
            commentRepository.save(comment);
            evaluation.getClass().getMethod("set" + firstUpperCase(parameterName) + "_Id", Long.class).invoke(evaluation, comment.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IMException("getCommentId or setCommentId may occur exception, updateComment fail");
        }
    }

    private void bankSave(HttpServletRequest request) {

    }

    private void agentSave(HttpServletRequest request) {

    }

    private void leaderSave(HttpServletRequest request) throws IMException {
        staffSave(request);
    }

    private void hrSave(HttpServletRequest request) {

    }

    private void adminSave(HttpServletRequest request) {

    }


    @Autowired
    private UserRepository userRepository;
    public List<User> getSubordinate(HttpServletRequest request) throws IMException {
        String dpCode = request.getParameter("dpCode");
        if(StringUtils.isEmpty(dpCode)){
            throw new IMException("dpCode is empty");
        }
        String year = request.getParameter("year");
        if(StringUtils.isEmpty(year)){
            throw new IMException("year is empty");
        }
//        Long allSubordinateCount = userRepository.countByDpCode(dpCode);
//        Long matchSubordinateCount = evaluationRepository.countByDpCodeAndYearAndStatus(dpCode,year,WorkflowConstant.PERSON_SUBMIT);
//        List<Evaluation> list = evaluationRepository.findByDpCodeAndYearAndStatus(dpCode,year,WorkflowConstant.PERSON_SUBMIT);
        List<User> list = userRepository.findAll();
        return list;
    }

}
