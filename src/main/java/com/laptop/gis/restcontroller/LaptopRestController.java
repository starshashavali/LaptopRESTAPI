package com.laptop.gis.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laptop.gis.constants.AppConstants;
import com.laptop.gis.dto.LaptopDto;
import com.laptop.gis.service.LaptopService;

@RestController
@RequestMapping("/api")
public class LaptopRestController {

	@Autowired
	LaptopService laptopService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody LaptopDto laptopDto) {
		boolean saveLaptop = laptopService.saveLaptop(laptopDto);
		if (saveLaptop) {
			return new ResponseEntity<>(AppConstants.SUCSAVE, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(AppConstants.FAILSAVE, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateLaptop(@PathVariable Integer id, @RequestBody LaptopDto laptopDto) {
		boolean updateLaptop = laptopService.updateLaptop(id, laptopDto);
		if (updateLaptop) {
			return new ResponseEntity<>(AppConstants.SUCUPDATE, HttpStatus.OK);
		}
		return new ResponseEntity<>(AppConstants.FAILUPDATE, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getLaptop/{id}")
	public ResponseEntity<LaptopDto> getLaptop(@PathVariable Integer id) {
		LaptopDto lapTopById = laptopService.getLapTopById(id);
		return new ResponseEntity<LaptopDto>(lapTopById, HttpStatus.OK);
	}

	@GetMapping("/getAllLaptop")
	public ResponseEntity<List<LaptopDto>> getAllLaptops() {
		List<LaptopDto> allLaptop = laptopService.getAllLaptop();
		return new ResponseEntity<List<LaptopDto>>(allLaptop, HttpStatus.OK);
	}

	@DeleteMapping("/deleteLaptop/{id}")
	public ResponseEntity<String> deleteLaptopById(@PathVariable Integer id) {
		laptopService.deleteLaptopById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
