package com.yiran.repositories;

import com.yiran.entities.BusinessEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Yiran on 17-1-23.
 */
@Component
public interface BusinessExesRepository extends JpaRepository<BusinessEx,Long>{
}
