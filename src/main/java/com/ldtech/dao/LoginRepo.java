package com.ldtech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ldtech.entity.AdminLogin;

public interface LoginRepo extends JpaRepository<AdminLogin,Integer>{
    
	@Query("select u from AdminLogin u where u.admin_id=:admin_id and u.password=:password")
	public AdminLogin validateForm(@Param("admin_id")String admin_id, @Param("password")String password);

	public AdminLogin findByEmail(String email);

	public AdminLogin findByResetToken(String resetToken);
}
