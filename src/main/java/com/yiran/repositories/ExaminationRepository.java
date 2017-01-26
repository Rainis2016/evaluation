package com.yiran.repositories;

import com.yiran.entities.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

/**
 * Created by Yiran on 17-1-23.
 */
@Component
public interface ExaminationRepository extends JpaRepository<Examination,Long>{
    @Query("select e from Examination e where e.workCode=:workCode and e.year=:year")
    Examination findByWorkCodeAndYear(@Param("workCode")String workCode, @Param("year")String year);
}
