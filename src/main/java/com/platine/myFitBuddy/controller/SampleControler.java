package com.platine.myFitBuddy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.platine.myFitBuddy.model.Sample;
import com.platine.myFitBuddy.repository.SampleRepository;

@RestController
@RequestMapping("/sample")
public class SampleControler {
    
    @Autowired
    SampleRepository sampleRepository;

    /**
     * Get all samples
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<Sample>> getAllSamples(){
        List<Sample> optional_samples = sampleRepository.findAll();
        return ResponseEntity.ok(optional_samples);
    }

    /**
     * Create a sample
     * @param createForm
     * @return
     */
    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Sample> create(@RequestBody final String name){
        Sample submittedSample = new Sample();
        submittedSample.setName(name);
        Sample newSample = sampleRepository.save(submittedSample);
        return ResponseEntity.ok(newSample);
    }


}

