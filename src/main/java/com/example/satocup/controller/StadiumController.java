// StadiumController.java
package com.example.satocup.controller;

import com.example.satocup.model.dto.StadiumDTO;
import com.example.satocup.model.dto.responseDto.StadiumRespDTO;
import com.example.satocup.service.StadiumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/stadiums")
public class StadiumController {

    private final StadiumService stadiumService;

    @Autowired
    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping
    public ResponseEntity<List<StadiumRespDTO>> getAllStadiums() {
        List<StadiumRespDTO> stadiums = stadiumService.getAllStadiums();
        return new ResponseEntity<>(stadiums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StadiumDTO> getStadiumById(@PathVariable("id") Long stadiumId) {
        StadiumDTO stadiumDTO = stadiumService.getStadiumById(stadiumId);
        return new ResponseEntity<>(stadiumDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StadiumDTO> createStadium(@Valid @RequestBody StadiumDTO stadiumDTO) {
        StadiumDTO createdStadiumDTO = stadiumService.createStadium(stadiumDTO);
        return new ResponseEntity<>(createdStadiumDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StadiumDTO> updateStadium(@PathVariable("id") Long stadiumId, @Valid @RequestBody StadiumDTO stadiumDTO) {
        StadiumDTO updatedStadiumDTO = stadiumService.updateStadium(stadiumId, stadiumDTO);
        return new ResponseEntity<>(updatedStadiumDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStadium(@PathVariable("id") Long stadiumId) {
        stadiumService.deleteStadium(stadiumId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
