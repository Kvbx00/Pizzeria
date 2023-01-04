package com.pizzeriaRestaurant.repository;

import com.pizzeriaRestaurant.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,String>{
	Admin findByUsername(String username);
	
	@Query(value="SELECT username FROM Admin",nativeQuery=true)
	public List<String> findUsenames();
}