package com.myclass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Video;

@RestController
@RequestMapping("/api/video")
public class VideoController extends GenericController<Video>{

	
}
