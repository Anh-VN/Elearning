package com.myclass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Role;

@RestController
@RequestMapping("/api/role")
public class RoleController extends GenericController<Role> {

}
