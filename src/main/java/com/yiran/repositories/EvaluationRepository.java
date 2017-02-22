package com.yiran.repositories;

import com.yiran.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Yiran on 17-1-23.
 */
@Component
public interface EvaluationRepository extends JpaRepository<Evaluation,Long>{

    Evaluation findByWorkCodeAndYear(String workCode, String year);

    Long countByWorkCodeAndYear(String workCode, String year);

    Long countByDpCodeAndYearAndStatus(String dpCode,String year,String status);

    List<Evaluation> findByDpCodeAndYearAndStatus(String dpCode,String year,String status);
}

//    @Query("select e from Evaluation e where e.workCode=:workCode and e.year=:year")
//    Evaluation findByWorkCodeAndYear(@Param("workCode")String workCode, @Param("year")String year);

//
//    @Query("select e from Evaluation e where e.workCode=:workCode and e.year=:year")
//    Long countByWorkCodeAndYear(@Param("workCode")String workCode, @Param("year")String year);