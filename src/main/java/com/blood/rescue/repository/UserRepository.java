package com.blood.rescue.repository;

import com.blood.rescue.entity.BloodGroup;
import com.blood.rescue.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailId(String emailId);

    List<User> findAllByDistrict(String district);
}
