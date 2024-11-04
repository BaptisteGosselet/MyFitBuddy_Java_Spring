package com.platine.myFitBuddy.features.fitSets.controller;

import org.springframework.web.bind.annotation.RestController;

import com.platine.myFitBuddy.features.fitSets.service.FitSetServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FitSetControllerImpl implements FitSetController {
    
    private final FitSetServiceImpl fitSetService;

}
