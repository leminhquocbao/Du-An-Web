package com.example.Duan.repository;

import com.example.Duan.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleResponsitory extends JpaRepository<Role, String> {
}
