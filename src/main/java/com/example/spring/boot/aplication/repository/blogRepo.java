package com.example.spring.boot.aplication.repository;

import com.example.spring.boot.aplication.Models.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface blogRepo extends JpaRepository<Blogs,Integer> {
}
