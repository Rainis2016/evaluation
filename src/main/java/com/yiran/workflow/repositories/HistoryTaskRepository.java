package com.yiran.workflow.repositories;

import com.yiran.workflow.entities.HistoryTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Yiran on 2017/2/6.
 */
@Component
public interface HistoryTaskRepository extends JpaRepository<HistoryTask,Long> {
    List<HistoryTask> findByBusinessKey(Long businessKey);
    HistoryTask findByBusinessKeyAndStatus(Long businessKey,String status);
}
