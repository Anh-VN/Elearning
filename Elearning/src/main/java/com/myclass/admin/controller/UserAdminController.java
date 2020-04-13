package com.myclass.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.dto.UserDto;
import com.myclass.entity.Course;
import com.myclass.entity.User;
import com.myclass.service.CourseService;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;
import com.myclass.validation.UserValidation;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController { // extends GenericAdminController<User> {

	@Autowired
	protected RoleService roleService;

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected CourseService courseService;
		
	@Autowired
	private UserValidation userValidation;

	@GetMapping("")
	public String getAll(Model model, HttpSession session) throws Exception {

		List<User> users = userService.findAll();

		model.addAttribute("users", users);

		return "user/user-admin";
	}

	@GetMapping("/add")
	public String add(Model model) {

		model.addAttribute("userDto", new UserDto());

		model.addAttribute("roles", roleService.findAll());

		return "user/user-add-admin";
	}

	@PostMapping("/add")
	public String addPost(Model model, @Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result) {

		userValidation.validate(userDto, result);

		if (result.hasErrors()) {
			model.addAttribute("roles", roleService.findAll());

			return "user/user-add-admin";
		}

		if (userDto.getPassword().equals(userDto.getConfirm())) {
			userDto.setId(0);
			userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
			userService.saveUserbyUserDto(userDto);
		}

		return "redirect:/admin/user";
	}

	@GetMapping("/edit")
	public String editGet(Model model, @RequestParam int id) {

		User user = userService.findById(id);

		model.addAttribute("userDto", userService.createUserDtoFromUser(user));

		model.addAttribute("roles", roleService.findAll());

		return "user/user-edit-admin";
	}

	@PostMapping("/edit")
	public String editPost(Model model, @Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result,
			HttpSession session) throws Exception {

		userValidation.validate(userDto, result);

		if (result.hasErrors()) {
			model.addAttribute("roles", roleService.findAll());

			return "user/user-edit-admin";
		}

		if (userDto.getPassword().equals(userDto.getConfirm())) {
			userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
			userService.saveUserbyUserDto(userDto);

			if (userService.updateAuth(userDto) != null) {
				// SPRING_SECURITY_CONTEXT session
				session.invalidate();
				return "redirect:/admin/login?state=reset";
			}
		}

		return "redirect:/admin/user";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") int id) {

		userService.deleteById(id);

		return "redirect:/admin/user";
	}

	@GetMapping("/addcourse")
	public String addCourse(@RequestParam(name = "userId") int userId, @RequestParam(name = "courseId") int courseId) {
		
		User user = userService.findById(userId);
		
		Course course = courseService.findById(courseId);
		user.addCourse(course);	
		userService.saveOrUpdate(user);
		
		return "redirect:/admin/user";

	}
}
