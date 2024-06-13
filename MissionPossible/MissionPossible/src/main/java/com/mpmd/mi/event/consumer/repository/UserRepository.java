package com.mpmd.mi.event.consumer.repository;

import com.mpmd.mi.event.consumer.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {

//    @Modifying
//    @Query(value = "INSERT INTO user_data VALUES (:id, :name)")
//    void add(@Param("id") String userID, @Param("name") String userName);


    //public UserData save(UserData userData);
}
