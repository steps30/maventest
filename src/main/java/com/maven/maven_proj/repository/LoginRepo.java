package com.maven.maven_proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maven.maven_proj.domain.Login;

@Repository
public interface LoginRepo extends JpaRepository<Login, String> {
   Login findByUsernameAndPassword(String username, String password);
}
