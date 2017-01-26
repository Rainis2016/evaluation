package com.yiran.entities;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Yiran on 17-1-16.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseModel {

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
    private String dpCode;

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
