package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Bagel;

public interface BagelRepository extends JpaRepository<Bagel, Long>
{

}
