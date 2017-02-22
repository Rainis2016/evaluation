package com.yiran.workflow.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Yiran on 2017/2/6.
 */
@Entity
@Table(name = "RunningTasks")
@Getter
@Setter
public class RunningTask extends BaseTask {
    private Long businessKey;
    private String taskName;
    private String status;
}
