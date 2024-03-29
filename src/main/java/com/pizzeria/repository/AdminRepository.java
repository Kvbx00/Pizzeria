package com.pizzeria.repository;

import com.pizzeria.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,String>{
	Admin findByEmail(String email);
	
	@Query(value="SELECT email FROM Admin",nativeQuery=true)
	public List<String> findUsenames();
}