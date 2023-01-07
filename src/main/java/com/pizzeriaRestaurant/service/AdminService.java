package com.pizzeriaRestaurant.service;


import com.pizzeriaRestaurant.model.Admin;
import com.pizzeriaRestaurant.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin getAdmin(String email) {
		return adminRepository.findByEmail(email);
	}

	public boolean loginVerify(String email, String password) {
		Admin admin = adminRepository.findByEmail(email);
        if (admin!= null && admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            return true;
        }
        return false;
	}

	public void updatePassword(Admin admin) {
		adminRepository.save(admin);
		
	}

}
