package com.example.Duan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Duan.entity.InvalidatedToken;

@Repository
public interface InvalidatedTokenResponsitory extends JpaRepository<InvalidatedToken, String> {}
