package com.laptop.gis;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptop.gis.constants.AppConstants;
import com.laptop.gis.dto.LaptopDto;
import com.laptop.gis.restcontroller.LaptopRestController;
import com.laptop.gis.service.LaptopService;

@WebMvcTest(LaptopRestController.class)
public class LaptopRestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	LaptopService laptopService;


    @Test
    void testSaveLaptopPositive() throws Exception {
        LaptopDto laptopDto = new LaptopDto(1,"64gb","HP");
      

        when(laptopService.saveLaptop(laptopDto)).thenReturn(true);

        mockMvc.perform(post("/api/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(laptopDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testSaveLaptopNegative() throws Exception {
        LaptopDto laptopDto = null;

        when(laptopService.saveLaptop(laptopDto)).thenReturn(false);

        mockMvc.perform(post("/api/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(laptopDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateLaptopPositive() throws Exception {
        Integer id = 1;
        LaptopDto laptopDto = new LaptopDto(id,"8gb","Hp");
      

        when(laptopService.updateLaptop(id, laptopDto)).thenReturn(true);

        mockMvc.perform(put("/api/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(laptopDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(AppConstants.SUCUPDATE));
    }

    @Test
    void testUpdateLaptop_Negative() throws Exception {
        Integer id = -1;
        LaptopDto laptopDto = new LaptopDto();

        when(laptopService.updateLaptop(id, laptopDto)).thenReturn(false);

        mockMvc.perform(put("/api/update/{id}", -1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(laptopDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetLaptop() throws Exception {
        Integer id = 1;
        LaptopDto laptopDto = new LaptopDto(id, "Lenovo", "8GB");

        when(laptopService.getLapTopById(id)).thenReturn(laptopDto);

        mockMvc.perform(get("/api/getLaptop/{id}", id))
                .andExpect(status().isOk());
             
    }

    @Test
    void testGetAllLaptops() throws Exception {
        List<LaptopDto> laptopDtoList = Arrays.asList(
                new LaptopDto(1, "Lenovo", "8GB"),
                new LaptopDto(2, "HP", "16GB")
        );

        when(laptopService.getAllLaptop()).thenReturn(laptopDtoList);

        mockMvc.perform(get("/api/getAllLaptop"))
                .andExpect(status().isOk());
              
    }

    @Test
    void testDeleteLaptopById() throws Exception {
        Integer id = 1;

        mockMvc.perform(delete("/api/deleteLaptop/{id}", id))
                .andExpect(status().isOk());

        verify(laptopService, times(1)).deleteLaptopById(id);
    }

    private static String asJsonString(final Object obj) throws JsonProcessingException {
       
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
     
}
}