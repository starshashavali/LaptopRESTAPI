package com.laptop.gis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptop.gis.domain.Laptop;
import com.laptop.gis.dto.LaptopDto;
import com.laptop.gis.repo.LaptopRepo;
import com.laptop.gis.service.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService {

	@Autowired
	LaptopRepo laptopRepo;

	@Override
	public boolean saveLaptop(LaptopDto laptopDto) {
		Laptop entity = new Laptop();
		BeanUtils.copyProperties(laptopDto, entity);
		laptopRepo.save(entity);
		return true;
	}

	@Override
	public boolean updateLaptop(Integer id, LaptopDto laptopDto) {
		Optional<Laptop> findById = laptopRepo.findById(id);
		if (findById.isPresent()) {
			Laptop laptop = findById.get();
			BeanUtils.copyProperties(laptopDto, laptop);
			laptopRepo.save(laptop);
			return true;
		}
		return false;
	}

	@Override
	public LaptopDto getLapTopById(Integer id) {
		Optional<Laptop> findById = laptopRepo.findById(id);
		if (findById.isPresent()) {
			Laptop laptop = findById.get();
			LaptopDto laptopDto = new LaptopDto();
			BeanUtils.copyProperties(laptop, laptopDto);
			return laptopDto;
		}
		return null;
	}

	@Override
	public List<LaptopDto> getAllLaptop() {
		List<Laptop> findAll = laptopRepo.findAll();
		List<LaptopDto> allDto= new ArrayList<>();
		for(Laptop entity:findAll) {
			LaptopDto dto =new LaptopDto();
			BeanUtils.copyProperties(entity, dto);
			allDto.add(dto);	
		}
		return allDto;
	}

	@Override
	public void deleteLaptopById(Integer id) {
		Optional<Laptop> findById = laptopRepo.findById(id);
		if(findById.isPresent()) {
			laptopRepo.deleteById(id);
		}
	}

}
