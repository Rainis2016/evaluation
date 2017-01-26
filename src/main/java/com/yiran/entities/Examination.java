package com.yiran.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Created by Yiran on 17-1-22.
 */
@Entity
@Table(name = "Examinations")
@Getter
@Setter
public class Examination extends BaseModel{
    @NonNull
    @Column(unique = true)
    private String workCode;
    @NonNull
    private String year;
    private Long score;
    private String level;
    private Long answer1_Id;
    private Long answer2_Id;
    private Long answer3_Id;
    private Long answer4_Id;
    private Long businessEx_Id;
    private Long otherEx_Id;
    private Long leaderComment_Id;
    private Long staffComment_Id;
    private Long leaderReward_Punish_Id;
    private Long hrReward_Punish_Id;
}
