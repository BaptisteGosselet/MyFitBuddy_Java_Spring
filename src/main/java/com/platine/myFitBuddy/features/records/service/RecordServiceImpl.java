package com.platine.myFitBuddy.features.records.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platine.myFitBuddy.features.records.repository.RecordRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    
    @Autowired
    private final RecordRepository recordRepository;

}
