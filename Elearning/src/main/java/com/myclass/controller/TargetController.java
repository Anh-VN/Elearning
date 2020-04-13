package com.myclass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Target;

@RestController
@RequestMapping("/api/target")
public class TargetController extends GenericController<Target>{

}
