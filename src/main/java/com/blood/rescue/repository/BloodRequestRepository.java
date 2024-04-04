package com.blood.rescue.repository;

import com.blood.rescue.entity.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodRequestRepository extends JpaRepository<BloodRequest,Long> {
}
