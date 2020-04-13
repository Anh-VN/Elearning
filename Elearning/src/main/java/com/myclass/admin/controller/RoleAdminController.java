package com.myclass.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Role;
import com.myclass.service.impl.GenericServiceImpl;

@Controller
@RequestMapping("/admin/role")
public class RoleAdminController extends GenericAdminController<Role>{

	@Autowired
	protected GenericServiceImpl<Role, Integer> roleService;
	
	/*
	 * @GetMapping("/add") public String add(Model model) {
	 * 
	 * model.addAttribute("role", new Role());
	 * 
	 * return "role/role-add-admin"; }
	 * 
	 * @PostMapping("/add") public String addPost(Model
	 * model, @ModelAttribute("role") Role role) {
	 * 
	 * service.saveOrUpdate(role);
	 * 
	 * return "redirect:/role";
	 * 
	 * }
	 * 
	 * @GetMapping("/edit") public String editGet(Model model, @RequestParam int id)
	 * {
	 * 
	 * Role role = service.findById(id);
	 * 
	 * model.addAttribute("role", role);
	 * 
	 * return "role/role-edit-admin"; }
	 * 
	 * @PostMapping("/edit") public String editPost(Model
	 * model, @ModelAttribute("role") Role role) {
	 * 
	 * service.saveOrUpdate(role);
	 * 
	 * return "redirect:/role"; }
	 */
}
