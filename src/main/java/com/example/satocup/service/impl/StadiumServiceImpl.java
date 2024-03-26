// StadiumServiceImpl.java
package com.example.satocup.service.impl;

import com.example.satocup.model.dto.StadiumDTO;
import com.example.satocup.model.dto.responseDto.StadiumRespDTO;
import com.example.satocup.model.entity.City;
import com.example.satocup.model.entity.Stadium;
import com.example.satocup.repository.CityRepository;
import com.example.satocup.repository.StadiumRepository;
import com.example.satocup.service.StadiumService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;
    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;

    @Autowired
    public StadiumServiceImpl(StadiumRepository stadiumRepository, ModelMapper modelMapper, CityRepository cityRepository) {
        this.stadiumRepository = stadiumRepository;
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<StadiumRespDTO> getAllStadiums() {
        try {
            List<Stadium> stadiums = stadiumRepository.findAll();
            return stadiums.stream()
                    .map(stadium -> modelMapper.map(stadium, StadiumRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all stadiums: " + e.getMessage());
        }
    }

    @Override
    public StadiumDTO getStadiumById(Long stadiumId) {
        try {
            Stadium stadium = stadiumRepository.findById(stadiumId)
                    .orElseThrow(() -> new NotFoundException("Stadium not found with ID: " + stadiumId));
            return modelMapper.map(stadium, StadiumDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stadium with ID " + stadiumId + ": " + e.getMessage());
        }
    }

    @Override
    public StadiumDTO createStadium(StadiumDTO stadiumDTO) {
        try {
            Stadium stadium = modelMapper.map(stadiumDTO, Stadium.class);
            stadium = stadiumRepository.save(stadium);
            return modelMapper.map(stadium, StadiumDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create stadium: " + e.getMessage());
        }
    }

    @Override
    public StadiumDTO updateStadium(Long stadiumId, StadiumDTO stadiumDTO) {
        try {
            Stadium existingStadium = stadiumRepository.findById(stadiumId)
                    .orElseThrow(() -> new NotFoundException("Stadium not found with ID: " + stadiumId));

            existingStadium.setName(stadiumDTO.getName());
            existingStadium.setCapacity(stadiumDTO.getCapacity());
            existingStadium.setLocation(stadiumDTO.getLocation());

            City city = cityRepository.findById(stadiumDTO.getCityId())
                    .orElseThrow(() -> new NotFoundException("City not found with ID: " + stadiumDTO.getCityId()));
            existingStadium.setCity(city);

            existingStadium = stadiumRepository.save(existingStadium);
            return modelMapper.map(existingStadium, StadiumDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update stadium with ID " + stadiumId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteStadium(Long stadiumId) {
        try {
            if (!stadiumRepository.existsById(stadiumId)) {
                throw new NotFoundException("Stadium not found with ID: " + stadiumId);
            }
            stadiumRepository.deleteById(stadiumId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete stadium with ID " + stadiumId);
        }
    }
}
