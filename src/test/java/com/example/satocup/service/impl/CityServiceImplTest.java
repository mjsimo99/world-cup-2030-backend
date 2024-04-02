package com.example.satocup.service.impl;

import com.example.satocup.model.dto.CityDTO;
import com.example.satocup.model.entity.City;
import com.example.satocup.repository.CityRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Validator validator;

    @InjectMocks
    private CityServiceImpl cityService;

    private City city;
    private CityDTO cityDTO;

    @BeforeEach
    void setUp() {

        city = new City(1L, "City1", null);
        cityDTO = new CityDTO(1L, "City1");
    }

    @Test
    void getAllCities() {
        List<City> cities = Collections.singletonList(city);
        List<CityDTO> cityDTOs = Collections.singletonList(cityDTO);

        when(cityRepository.findAll()).thenReturn(cities);
        when(modelMapper.map(city, CityDTO.class)).thenReturn(cityDTO);

        List<CityDTO> result = cityService.getAllCities();

        assertEquals(cityDTOs, result);
        verify(cityRepository).findAll();
        verify(modelMapper).map(city, CityDTO.class);
    }

    @Test
    void getCityById() {
        when(cityRepository.findById(1L)).thenReturn(java.util.Optional.of(city));
        when(modelMapper.map(city, CityDTO.class)).thenReturn(cityDTO);

        CityDTO result = cityService.getCityById(1L);

        assertEquals(cityDTO, result);
        verify(cityRepository).findById(1L);
        verify(modelMapper).map(city, CityDTO.class);
    }

    @Test
    void createCity() {
        when(validator.validate(cityDTO)).thenReturn(Collections.emptySet());
        when(modelMapper.map(cityDTO, City.class)).thenReturn(city);
        when(cityRepository.save(city)).thenReturn(city);
        when(modelMapper.map(city, CityDTO.class)).thenReturn(cityDTO);

        CityDTO result = cityService.createCity(cityDTO);

        assertEquals(cityDTO, result);
        verify(validator).validate(cityDTO);
        verify(modelMapper).map(cityDTO, City.class);
        verify(cityRepository).save(city);
        verify(modelMapper).map(city, CityDTO.class);
    }

    @Test
    void createCityWithInvalidCityDTO() {
        ConstraintViolation<CityDTO> violation = mock(ConstraintViolation.class);
        when(validator.validate(cityDTO)).thenReturn(Collections.singleton(violation));

        assertThrows(RuntimeException.class, () -> cityService.createCity(cityDTO));

        verify(validator).validate(cityDTO);
    }




}