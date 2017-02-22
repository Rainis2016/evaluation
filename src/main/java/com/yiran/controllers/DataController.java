package com.yiran.controllers;

import com.yiran.entities.Answer;
import com.yiran.entities.Comment;
import com.yiran.entities.Evaluation;
import com.yiran.entities.User;
import com.yiran.repositories.AnswerRepository;
import com.yiran.repositories.CommentRepository;
import com.yiran.repositories.EvaluationRepository;
import com.yiran.repositories.UserRepository;
import com.yiran.result.IMException;
import com.yiran.service.EvaluationService;
import com.yiran.service.UserService;
import com.yiran.workflow.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Yiran on 2017/1/31.
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public User getUser(HttpServletRequest request) throws IMException {
//        String workCode = userService.currentUser().getWorkCode();
        String workCode = request.getParameter("workCode");
        if(StringUtils.isEmpty(workCode)){
            throw new IMException("user's workCode is Empty ,please check SecurityContext");
        }else {
            return userRepository.findByWorkCode(workCode);
        }
    }

    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping("/getAnswer")
    public Answer getAnswer(HttpServletRequest request){
        String idStr = request.getParameter("answerId");
        if (!StringUtils.isEmpty(idStr) ){
            Long id = Long.parseLong(idStr);
            return answerRepository.findOne(id);
        }
        return null;
    }

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping("/getComment")
    public Comment getComment(HttpServletRequest request){
        String idStr = request.getParameter("commentId");
        if (!StringUtils.isEmpty(idStr) ){
            Long id = Long.parseLong(idStr);
            return commentRepository.findOne(id);
        }
        return null;
    }

    @Autowired
    private EvaluationRepository evaluationRepository;

    @RequestMapping("/getEvaluation")
    public Evaluation getEvaluation(HttpServletRequest request) throws IMException {
        String workCode = request.getParameter("workCode");
        if(StringUtils.isEmpty(workCode)){
            throw new IMException("workCode is Empty, please check HttpServletRequest");
        }
        String year = request.getParameter("year");
        if(StringUtils.isEmpty(year)){
            throw new IMException("year is Empty, please check HttpServletRequest");
        }
        return evaluationRepository.findByWorkCodeAndYear(workCode,year);
    }


    @Autowired
    private WorkflowService workflowService;

    @RequestMapping("/getStatus")
    public String getStatus(HttpServletRequest request) throws IMException {
        String idStr = request.getParameter("id");
        if(idStr==null){
            throw new IMException("id is null");
        }
        Long id = Long.parseLong(idStr);
        return workflowService.getStatus(id);
    }

    @Autowired
    private EvaluationService evaluationService;

    @RequestMapping("/saveEvaluation")
    public void saveEvaluation(HttpServletRequest request) throws IMException{
        User currentUser = userService.currentUser();
        evaluationService.save(currentUser,request);
    }

    @RequestMapping("/submitPersonWf")
    public void submitEvaluation(HttpServletRequest request)throws IMException{
        User currentUser = userService.currentUser();
        evaluationService.submitPersonWf(currentUser,request);
    }


    @RequestMapping("/getSubordinate")
    public List<User> getSubordinate(HttpServletRequest request) throws IMException {
        return evaluationService.getSubordinate(request);
    }
}
