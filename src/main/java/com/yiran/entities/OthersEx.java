package com.yiran.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Yiran on 17-1-22.
 */
@Entity
@Table(name = "OtherExes")
@Getter
@Setter
public class OthersEx extends BaseModel{
    private Long weight1;
    private Long weight2;
    private Long weight3;
    private Long weight4;
    private Long Score1;
    private Long Score2;
    private Long Score3;
    private Long Score4;
}
