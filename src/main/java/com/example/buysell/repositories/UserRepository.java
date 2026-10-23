package com.example.buysell.repositories;

import com.example.buysell.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findById(long id);
}
