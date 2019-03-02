package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Topping;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long>
{

}
