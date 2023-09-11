package com.laptop.gis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.laptop.gis.domain.Laptop;
import com.laptop.gis.dto.LaptopDto;
import com.laptop.gis.repo.LaptopRepo;
import com.laptop.gis.service.impl.LaptopServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class LaptopServiceImplTest {
	@Mock
	LaptopRepo laptopRepo;

	@InjectMocks
	LaptopServiceImpl laptopServiceImpl;

	@Test
	@Order(1)
	void testPositiveSaveLaptop() {

		Laptop entity = new Laptop(1, "64Gb", "HP");

		LaptopDto dto = new LaptopDto();

		BeanUtils.copyProperties(entity, dto);

		when(laptopRepo.save(entity)).thenReturn(entity);

		assertTrue(laptopServiceImpl.saveLaptop(dto));

	}

	@Test
	@Order(4)
	void testUpdateLaptop() {
		Integer id = 10;
		Laptop entity = new Laptop(1, "8gb", "Hp");
		LaptopDto dto = new LaptopDto();
		BeanUtils.copyProperties(entity, dto);
		when(laptopRepo.findById(id)).thenReturn(Optional.of(entity));
		assertTrue(laptopServiceImpl.updateLaptop(id, dto));

	}

	@Test
	@Order(5)
	void testNegativeUpdateLaptop() {
		Integer id = 10;
		Laptop entity = new Laptop(1, "8gb", "Hp");
		LaptopDto dto = new LaptopDto();
		BeanUtils.copyProperties(entity, dto);
		when(laptopRepo.findById(id)).thenReturn(Optional.empty());
		assertFalse(laptopServiceImpl.updateLaptop(id, dto));
	}

	@Test
	@Order(3)
	void testPositiveGetLapTopById() {
		Integer id = 10;
		Laptop entity = new Laptop(id, "Lenova", "8gb");
		when(laptopRepo.findById(id)).thenReturn(Optional.of(entity));
		assertEquals(id, laptopServiceImpl.getLapTopById(id).getLaptopId());
	}

	@Test
	@Order(2)
	void testGetAllLaptop() {

		List<Laptop> allLaptops = new ArrayList<>();
		allLaptops.add(new Laptop(1, "8GB", "HP"));
		allLaptops.add(new Laptop(2, "64GB", "HP"));

		when(laptopRepo.findAll()).thenReturn(allLaptops);

		List<LaptopDto> allLaptop = laptopServiceImpl.getAllLaptop();
		assertEquals(2, allLaptop.size());

	}

	@Test
	public void testDeleteLaptopById() {
		Integer id = 1;
		Laptop entity = new Laptop(id, "64Gb", "Hp");
		when(laptopRepo.findById(id)).thenReturn(Optional.of(entity));
		laptopServiceImpl.deleteLaptopById(id);
		verify(laptopRepo, times(1)).deleteById(id);
	}

}
