package com.laptop.gis.service;

import java.util.List;

import com.laptop.gis.dto.LaptopDto;

public interface LaptopService {

	public boolean saveLaptop(LaptopDto laptopDto);
	
	public boolean updateLaptop(Integer id,LaptopDto laptopDto);
	
	public LaptopDto getLapTopById(Integer id);
	
	public List<LaptopDto> getAllLaptop();
	
	public void deleteLaptopById(Integer id);
	
	
}
