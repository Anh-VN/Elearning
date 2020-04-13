package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclass.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Integer> {

}
