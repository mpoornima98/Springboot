package com.mpmd.mi.event.consumer.repository;

import com.mpmd.mi.event.consumer.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {

    @Query(value = "SELECT * FROM user_data WHERE user_id = :id ",nativeQuery = true)
    UserData findByUserID(@Param("id") String userID);


}
