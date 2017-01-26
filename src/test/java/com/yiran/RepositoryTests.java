package com.yiran;

import com.yiran.entities.*;
import com.yiran.repositories.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by Yiran on 17-1-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Test
    public void userRepositoryTest(){
        List<User> list = userRepository.findAll();
    }

    @Autowired
    private BusinessExesRepository businessExesRepository;
    @Test
    public void businessExesRepository(){
        List<BusinessEx> list  = businessExesRepository.findAll();
    }

    @Autowired
    private OthersExesRepository othersExesRepository;
    @Test
    public void othersERepositoryTest(){
        List<OthersEx> list = othersExesRepository.findAll();
    }

    @Autowired
    private CommentRepository commentRepository;
    @Test
    public void commentRepositoryTest(){
        List<Comment> list = commentRepository.findAll();
    }

    @Autowired
    private AnswerRepository answerRepository;
    @Test
    public void answerTest(){
        List<Answer> list  = answerRepository.findAll();
    }

    @Autowired
    private ExaminationRepository examinationRepository;
    @Test
    public void examinationRepositoryTest(){
        List<Examination> list = examinationRepository.findAll();
    }
}
