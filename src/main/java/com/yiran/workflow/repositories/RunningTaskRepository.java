package com.yiran.workflow.repositories;

import com.yiran.workflow.entities.RunningTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Yiran on 2017/2/6.
 */
@Component
public interface RunningTaskRepository extends JpaRepository<RunningTask,Long> {
    RunningTask findByBusinessKey(Long businessKey);
    Long countByBusinessKey(Long businessKey);
}
