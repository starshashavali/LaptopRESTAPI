package com.laptop.gis.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptop.gis.domain.Laptop;

public interface LaptopRepo extends JpaRepository<Laptop, Integer>{

}
