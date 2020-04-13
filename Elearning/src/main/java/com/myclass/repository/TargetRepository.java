package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclass.entity.Target;

public interface TargetRepository extends JpaRepository<Target, Integer> {

}
