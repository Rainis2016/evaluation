package com.yiran.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Yiran on 17-1-17.
 */
@MappedSuperclass
@Getter
@Setter
public class BaseModel implements Comparable<BaseModel>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    //    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    //    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = updatedAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        return this.getId().equals(((BaseModel) other).getId());
    }

//    public int hashCode() {
//        return new HashCodeBuilder().append(getId()).toHashCode();
//    }


    @Override
    public int compareTo(BaseModel o) {
        return this.getId().compareTo(o.getId());
    }

}
