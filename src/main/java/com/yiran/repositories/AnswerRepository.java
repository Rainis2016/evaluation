package com.yiran.repositories;

import com.yiran.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by Yiran on 17-1-22.
 */
@Component
public interface AnswerRepository extends JpaRepository<Answer,Long>{

}
