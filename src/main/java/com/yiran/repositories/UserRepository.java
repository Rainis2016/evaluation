package com.yiran.repositories;

import com.yiran.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Yiran on 17-1-16.
 */
@Component
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername (String username);
    User findByWorkCode (String workCode);
    List<User> findByBranchCode(String branchCode);
    Long countByDpCode(String dpCode);
}
