package com.example.satocup.service.impl;

import com.example.satocup.model.dto.CityDTO;
import com.example.satocup.model.entity.City;
import com.example.satocup.repository.CityRepository;
import com.example.satocup.service.CityService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CityDTO> getAllCities() {
        try {
            List<City> cities = cityRepository.findAll();
            return cities.stream()
                    .map(city -> modelMapper.map(city, CityDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all cities: " + e.getMessage());
        }
    }

    @Override
    public CityDTO getCityById(Long cityId) {
        try {
            City city = cityRepository.findById(cityId)
                    .orElseThrow(() -> new NotFoundException("City not found with ID: " + cityId));
            return modelMapper.map(city, CityDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch city with ID " + cityId + ": " + e.getMessage());
        }
    }

    @Override
    public CityDTO createCity(CityDTO cityDTO) {
        try {
            City city = modelMapper.map(cityDTO, City.class);
            city = cityRepository.save(city);
            return modelMapper.map(city, CityDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create city: " + e.getMessage());
        }
    }

    @Override
    public CityDTO updateCity(Long cityId, CityDTO cityDTO) {
        try {
            City existingCity = cityRepository.findById(cityId)
                    .orElseThrow(() -> new NotFoundException("City not found with ID: " + cityId));
            modelMapper.map(cityDTO, existingCity);
            existingCity.setCityId(cityId);
            existingCity = cityRepository.save(existingCity);
            return modelMapper.map(existingCity, CityDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update city with ID " + cityId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteCity(Long cityId) {
        try {
            if (!cityRepository.existsById(cityId)) {
                throw new NotFoundException("City not found with ID: " + cityId);
            }
            cityRepository.deleteById(cityId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete city with ID " + cityId);
        }
    }
}
