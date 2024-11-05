package com.platine.myFitBuddy.features.records.service;

import com.platine.myFitBuddy.features.records.repository.FitRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FitRecordServiceImpl implements FitRecordService {
  @Autowired
  private final FitRecordRepository recordRepository;
}
