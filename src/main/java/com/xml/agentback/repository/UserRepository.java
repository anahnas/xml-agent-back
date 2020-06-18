package com.xml.agentback.repository;

import com.xml.agentback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
        List<User> findAll();
        void deleteById(Long id);
        User save(User user);
        User findByUsername(String username);
}
