package com.solomon.user.repository;

import com.solomon.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailAndLastName(String email, String lastName);
    List<User> findByLastName(String lastName);
    List<User> findByFirstName(String firstName);
    User findByEmail(String email);

    User findByUserId(Long userId);


}
