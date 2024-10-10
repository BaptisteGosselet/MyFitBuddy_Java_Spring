package com.platine.myFitBuddy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platine.myFitBuddy.model.Sample;
import com.platine.myFitBuddy.repository.SampleRepository;

@RestController
@RequestMapping("/sample")
public class SampleControler {
    
    @Autowired
    SampleRepository sampleRepository;

    @GetMapping("/")
    public ResponseEntity<List<Sample>> getAllSamples(){
        List<Sample> optional_samples = sampleRepository.findAll();
        return ResponseEntity.ok(optional_samples);
    }

}

