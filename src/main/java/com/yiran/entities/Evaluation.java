package com.yiran.entities;

import com.yiran.workflow.constant.WorkflowConstant;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Yiran on 17-1-22.
 */
@Entity
@Table(name = "Evaluations")
@Getter
@Setter
public class Evaluation extends BaseModel{
    @NonNull
    private String workCode;
    @NonNull
    private String year;
    @NonNull
    private String type;

    private Long score;
    private String level;
    private Long answer1_Id;
    private Long answer2_Id;
    private Long answer3_Id;
    private Long answer4_Id;
    private Long businessEx_weight;
    private Long businessEx_Id;
    private Long otherEx_weight;
    private Long otherEx_Id;
    private Long leaderComment_Id;
    private Long staffComment_Id;
    private Long leaderReward_Punish_Id;
    private Long hrReward_Punish_Id;
    private String status;
    private String dpCode;
    private String branchCode;
}
