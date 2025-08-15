package com.example.Duan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Duan.entity.Role;

@Repository
public interface RoleResponsitory extends JpaRepository<Role, String> {}
