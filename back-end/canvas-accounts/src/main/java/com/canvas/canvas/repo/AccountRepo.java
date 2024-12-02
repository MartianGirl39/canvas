package com.canvas.canvas.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canvas.canvas.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, String>{
	public Account findAccountByUsername(String username);
	public Account findAccountByUser(long user);
	public Page<Account> findByUsernameContainingIgnoreCase(String keyword, Pageable pageable);
}
