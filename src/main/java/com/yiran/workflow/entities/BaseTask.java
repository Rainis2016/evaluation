package com.yiran.workflow.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Yiran on 2017/2/6.
 */
@MappedSuperclass
@Getter
@Setter
public class BaseTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    //    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.fff", timezone = "GMT+8")
    private Date createdAt;

    //    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.fff", timezone = "GMT+8")
    private Date updatedAt;

//    private String createdBy;
//
//    private String updatedBy;


    @PrePersist
    public void prePersist() {
        createdAt = updatedAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }
}
