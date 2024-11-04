package com.platine.myFitBuddy.features.fitSets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platine.myFitBuddy.features.fitSets.repository.FitSetRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FitSetServiceImpl implements FitSetService{
    
    @Autowired
    private final FitSetRepository fitSetRepository;


}
