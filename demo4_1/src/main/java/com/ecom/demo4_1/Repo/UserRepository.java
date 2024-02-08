package com.ecom.demo4_1.Repo;

import com.ecom.demo4_1.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
