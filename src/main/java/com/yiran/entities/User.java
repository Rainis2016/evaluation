package com.yiran.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Yiran on 17-1-16.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseModel  {

    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String role;
    @NonNull
    @Column(unique = true)
    private String workCode;
    @NonNull
    private String branchCode;
    @NonNull
    private String branchName;
    @NonNull
    private String dpCode;
    @NonNull
    private String dpName;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date entryDate;
    @NonNull
    private String postTitle;
    @NonNull
    private String professionTitle;

    public String toString() {
        return "{" +
                "username:" +username+
                "|password:" +password+
                "|role:"+role+
                "|workCode:" +workCode+
                "|branchCode:" +branchCode+
                "|dpCode:" +dpCode+
                "}";
    }
}
