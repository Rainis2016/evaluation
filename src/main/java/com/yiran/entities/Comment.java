package com.yiran.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Yiran on 17-1-22.
 */
@Entity
@Table(name = "Comments")
@Getter
@Setter
public class Comment extends BaseModel{
    private String type;
    private String comment;
    private String workCode;
    private String signName;
    private Date SignDate;
}
