package com.blood.rescue.repository;

import com.blood.rescue.entity.BloodGroup;
import com.blood.rescue.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmailId(String emailId);



    @Query(value = "SELECT * FROM USER u WHERE u.district=:district AND u.blood_group=:bg",nativeQuery = true)
    List<User> findPotentialMatch(@Param("district") String district, @Param("bg") BloodGroup bloodGroup);

    List<User> findByDistrict(String district);

}
