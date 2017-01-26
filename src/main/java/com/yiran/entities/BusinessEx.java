package com.yiran.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Yiran on 17-1-22.
 */
@Entity
@Table(name = "BusinessExes")
@Getter
@Setter
public class BusinessEx extends BaseModel{
    private Long weight;
    private Long col_1_1;
    private Long col_1_1_1;
    private Long col_1_2;
    private Long col_1_3;
    private Long col_2_1;
    private Long col_2_1_1;
    private Long col_2_2;
    private Long col_2_3;
    private Long col_3_1;
    private Long col_3_1_1;
    private Long col_3_2;
    private Long col_3_3;
}
