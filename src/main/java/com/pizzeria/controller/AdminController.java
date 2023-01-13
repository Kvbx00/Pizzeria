package com.pizzeria.controller;

import com.pizzeria.model.Admin;
import com.pizzeria.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/getDashboard")
	public String getDashboard() {
		return "adminDashboard";
	}
	
	@GetMapping("/changePassword")
	public String changeAdminPassword(HttpSession session, Model model)	{
		String username=(String) session.getAttribute("uname");
		Admin admin = adminService.getAdmin(username);
		model.addAttribute("admin", admin);
		return "change_password";
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam(name="oldPassword") String oldPassword,@RequestParam(name="newPassword") String newPassword,HttpSession session,Model model) {
		String username=(String) session.getAttribute("uname");
		Admin admin = adminService.getAdmin(username);
		if(oldPassword.equals(admin.getPassword())) {
			admin.setPassword(newPassword);
			adminService.updatePassword(admin);
			model.addAttribute("action", "Hasło zostało zmienione pomyślnie");
			return "adminDashboard";
		}else {
			model.addAttribute("action", "Stare hasło jest niepoprawne");
			return "change_password";
		}
		
	}
	
	@GetMapping("/logout")
	public String adminLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
