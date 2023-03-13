package com.example.TaxiPark.repository;


import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.model.entyti.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepos extends JpaRepository<User, Long> {

    User findUserByName(String name);

    List<User> findAllByRole(UserRole role);

    @Transactional
    @Modifying
    @Query("update User u set u.homeAddress = ?1 where u.id = ?2")
    void setHomeAddressById(String homeAddress, Long userId);
}
