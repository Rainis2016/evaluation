package com.yiran.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Created by Yiran on 17-1-22.
 */
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Answers")
@Getter
@Setter
public class Answer extends BaseModel {
    @Lob
    private String answer;

}
