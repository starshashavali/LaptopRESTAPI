package com.laptop.gis.dto;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopDto {
	
	private Integer laptopId;
	@NotNull
	private String size;
	@NotNull
	private String companyName;

}
