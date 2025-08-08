package com.example.Duan.repository;

import com.example.Duan.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisionResponsitory extends JpaRepository<Permission, String> {
}
