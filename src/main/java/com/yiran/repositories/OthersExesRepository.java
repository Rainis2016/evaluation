package com.yiran.repositories;

import com.yiran.entities.OthersEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Yiran on 17-1-23.
 */
@Component
public interface OthersExesRepository extends JpaRepository<OthersEx,Long>{
}
