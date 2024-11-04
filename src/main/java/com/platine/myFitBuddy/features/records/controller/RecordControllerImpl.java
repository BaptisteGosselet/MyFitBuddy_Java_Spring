package com.platine.myFitBuddy.features.records.controller;

import org.springframework.web.bind.annotation.RestController;

import com.platine.myFitBuddy.features.records.service.RecordServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecordControllerImpl {
    
    private final RecordServiceImpl recordService;

}
