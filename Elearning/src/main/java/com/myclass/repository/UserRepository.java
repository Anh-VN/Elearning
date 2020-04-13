package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myclass.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
}
