package com.example.Duan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Duan.entity.Permission;

@Repository
public interface PermisionResponsitory extends JpaRepository<Permission, String> {}
