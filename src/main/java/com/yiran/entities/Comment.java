package com.yiran.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Yiran on 17-1-22.
 */
@Entity
@Table(name = "Comments")
@Getter
@Setter
@ToString
public class Comment extends BaseModel{
    private String type;
    @Lob
    private String comment;
    private String workCode;
    private String signName;
    private String SignDate;
}
