package com.canvas.canvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canvas.canvas.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	Account findByUserId(long user);
	Account findByUsername(String username);
	boolean existsByUsername(String username);
}
