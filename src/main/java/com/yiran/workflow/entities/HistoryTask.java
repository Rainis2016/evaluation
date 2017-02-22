package com.yiran.workflow.entities;

import com.yiran.entities.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Yiran on 2017/2/6.
 */
@Entity
@Table(name = "HistoryTasks")
@Getter
@Setter
public class HistoryTask extends BaseTask {
    private Long businessKey;
    private String taskName;
    private String status;
}
