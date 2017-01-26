package com.yiran.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Yiran on 17-1-22.
 */
@Entity
@Table(name = "Answers")
@Getter
@Setter
public class Answer extends BaseModel {
    private String answer;

    public String toString(){
        return "{" +
                ""+answer+
                "}";
    }
}
