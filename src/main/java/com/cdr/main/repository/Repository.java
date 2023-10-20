package com.cdr.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdr.main.entity.Registration;

public interface Repository extends JpaRepository<Registration, Integer> {
	List<Registration> findByPhone(String phone);
    
}
